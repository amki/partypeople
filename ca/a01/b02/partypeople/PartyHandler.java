package ca.a01.b02.partypeople;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;

public class PartyHandler {

    private final PartyModel pModel;

    public PartyHandler(PartyModel pModel) {
        this.pModel = pModel;
    }

    public Party createParty(EntityPlayerMP leader) {
        Party p = new Party(this.pModel.nextPartyId, leader.username);
        this.pModel.nextPartyId++;
        PartyPlayer pp = new PartyPlayer(p.id, leader);
        this.pModel.partyplayer.put(leader.username, pp);
        this.pModel.parties.put(p.id, p);
        leader.sendChatToPlayer("Party with ID " + p.id + " created.");
        System.out.println("There are now " + this.pModel.parties.size() + " parties.");
        try {
            this.sendPartyJoin(p, leader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return p;
    }

    public Party invitePlayer(EntityPlayerMP inviter, EntityPlayerMP invitee) {
        // Fetch the inviter's party
        Party p = this.getPartyByPlayer(inviter);
        // If there is none gtfo
        if (p == null) {
            inviter.sendChatToPlayer("You are not in a party.");
            return null;
        }
        // Put invitee in invitee list
        this.pModel.inviteplayer.put(invitee.username, p.id);
        inviter.sendChatToPlayer("Player " + invitee.username + " has been invited to party " + p.id + ".");
        invitee.sendChatToPlayer("Player " + inviter.username + " wants to invite you to party " + p.id
                + ". /pp accept/decline");
        return p;
    }

    public Party joinParty(EntityPlayerMP player) {
        // Fetch the party id from the invitee list
        Integer pid = this.pModel.inviteplayer.get(player.username);
        if (pid == null) {
            player.sendChatToPlayer("You have not been invited to a party.");
            return null;
        }
        // Remove user from invitee list since it has responded
        this.pModel.inviteplayer.remove(player.username);
        Party p = this.getPartyById(pid);
        if (p == null) {
            player.sendChatToPlayer("You have not been invited to a party or the party does no longer exist.");
            return null;
        }
        p.members.add(player.username);
        PartyPlayer pp = new PartyPlayer(p.id, player);
        this.pModel.partyplayer.put(player.username, pp);
        try {
            this.sendPartyJoin(p, player);
            this.sendPartyPlayerJoin(p, player);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        player.sendChatToPlayer("You have joined party " + p.id);
        return p;
    }

    public Party declineParty(EntityPlayerMP player) {
        // Fetch the party id from the invitee list
        Integer pid = this.pModel.inviteplayer.get(player.username);
        if (pid == null) {
            player.sendChatToPlayer("You have not been invited to a party.");
            return null;
        }
        // Remove user from invitee list since it has responded
        this.pModel.inviteplayer.remove(player.username);
        player.sendChatToPlayer("You have declined to join party " + pid);
        return null;
    }

    public Party claimParty(EntityPlayerMP player) {
        PartyPlayer pp = this.pModel.partyplayer.get(player.username);
        if (pp == null) {
            player.sendChatToPlayer("You are not in a party.");
            return null;
        }
        Party p = this.getPartyById(pp.partyId);
        if (p == null) {
            // If p is null the party does not exist, remove the player from the list of partied players
            this.pModel.partyplayer.remove(player.username);
            player.sendChatToPlayer("You have are not in a party or the party does no longer exist.");
            return null;
        }
        // If the party leader is empty
        if (p.leader == "") {
            // Remove the leader from the members list
            p.members.remove(player.username);
            // Make it leader!
            p.leader = player.username;
            player.sendChatToPlayer("You have claimed party lead.");
        } else {
            player.sendChatToPlayer("There is already an active party leader.");
        }
        return p;
    }

    public Party quitParty(EntityPlayerMP player) {
        Party p = this.getPartyByPlayer(player);
        this.pModel.partyplayer.remove(player.username);
        if (p == null) {
            player.sendChatToPlayer("Either you were not in a party or it has stopped existing.");
            return null;
        }
        if (p.members.contains(player.username)) {
            p.members.remove(player.username);
        } else {
            p.leader = "";
        }
        player.sendChatToPlayer("You have left party " + p.id);
        try {
            this.sendPartyLeave(p, player);
            this.sendPartyPlayerLeave(p, player);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return p;
    }

    private void sendPartyJoin(Party party, EntityPlayerMP player) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(this.pModel.OPCODE_PARTY_JOIN);
        int size = 0;
        if (!party.leader.isEmpty()) {
            size++;
        }
        size += party.members.size();
        dos.writeByte(size);
        if (!party.leader.isEmpty()) {
            PartyPlayer leader = this.pModel.partyplayer.get(party.leader);
            dos.write(leader.serialize());
        }
        for (String member : party.members) {
            PartyPlayer m = this.pModel.partyplayer.get(member);
            dos.write(m.serialize());
        }
        dos.flush();
        NetworkHelper.sendToPlayer(baos, player);
    }

    private void sendPartyPlayerJoin(Party party, EntityPlayerMP player) throws IOException {
        // Build the packet containing the new player
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(this.pModel.OPCODE_PARTY_UPDATE);
        dos.writeByte(this.pModel.SUBCODE_PLAYER_JOIN);
        dos.writeByte(1);
        PartyPlayer pp = this.pModel.partyplayer.get(player.username);
        dos.write(pp.serialize());
        dos.flush();
        NetworkHelper.broadcastToPartyExceptPlayer(baos, party, player);
    }

    private void sendPartyLeave(Party party, EntityPlayerMP player) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(this.pModel.OPCODE_PARTY_LEAVE);
        dos.flush();
        NetworkHelper.sendToPlayer(baos, player);
    }

    private void sendPartyPlayerLeave(Party party, EntityPlayerMP player) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(this.pModel.OPCODE_PARTY_UPDATE);
        dos.writeByte(this.pModel.SUBCODE_PLAYER_LEAVE);
        dos.writeByte(1);
        PartyPlayer pp = this.pModel.partyplayer.get(player.username);
        dos.write(pp.serialize());
        dos.flush();
        NetworkHelper.broadcastToPartyExceptPlayer(baos, party, player);
    }

    public Party getPartyByPlayer(EntityPlayerMP player) {
        PartyPlayer pp = this.pModel.partyplayer.get(player.username);
        if (pp == null) {
            player.sendChatToPlayer("You are not in a party.");
            return null;
        }
        return this.getPartyById(pp.partyId);
    }

    public static Party getPartyByPlayer(PartyModel pModel, EntityPlayerMP player) {
        PartyPlayer pp = pModel.partyplayer.get(player.username);
        if (pp == null) {
            player.sendChatToPlayer("You are not in a party.");
            return null;
        }
        return getPartyById(pModel, pp.partyId);
    }

    public Party getPartyById(int id) {
        return this.pModel.parties.get(id);
    }

    public static Party getPartyById(PartyModel pModel, int id) {
        return pModel.parties.get(id);
    }
}
