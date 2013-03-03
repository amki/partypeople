package ca.a01.b02.partypeople;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {

    public PacketHandler() {
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side != Side.CLIENT) {
            return;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
            DataInputStream dis = new DataInputStream(bis);
            byte opcode = dis.readByte();
            System.out.println("Got packet with opcode " + opcode);
            switch (opcode) {
                case PartyModel.OPCODE_PARTY_JOIN:
                    this.handlePartyJoin(dis);
                    break;
                case PartyModel.OPCODE_PARTY_LEAVE:
                    this.handlePartyLeave(dis);
                    break;
                case PartyModel.OPCODE_PARTY_UPDATE:
                    this.handlePartyUpdate(dis);
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void handlePartyJoin(DataInputStream dis) throws IOException {
        System.out.println("Got party join packet!");
        byte playerc = dis.readByte();
        System.out.println("Found " + playerc + " players!");
        ArrayList<PartyPlayer> players = new ArrayList<PartyPlayer>();
        for (int i = 1; i <= playerc; i++) {
            PartyPlayer p = new PartyPlayer(dis);
            System.out.println("Found player " + p.username);
            players.add(p);
        }
        // bawki: TODO: players contains all players currently in party. DO SOMETHING
    }

    private void handlePartyLeave(DataInputStream dis) {
        System.out.println("Got party leave packet!");
        // bawki: TODO: we have left the party... DO SOMETHING
    }

    private void handlePartyUpdate(DataInputStream dis) throws IOException {
        System.out.println("Got party update packet!");
        byte subcode = dis.readByte();
        byte playerc = dis.readByte();
        System.out.println("Found " + playerc + " players!");
        switch (subcode) {
            case PartyModel.SUBCODE_PLAYER_UPDATE:
                PartyPlayer pp = new PartyPlayer(dis);
                System.out.println("\t This was a player update for " + pp.username);
                break;
            case PartyModel.SUBCODE_PLAYER_JOIN:
                System.out.println("\t This was player join");
                break;
            case PartyModel.SUBCODE_PLAYER_LEAVE:
                System.out.println("\t This was player leave");
                break;
        }

    }
}
