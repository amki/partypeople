package ca.a01.b02.partypeople;

import net.minecraft.entity.player.EntityPlayer;

public class PartyHandler {
	private PartyModel pModel;
	public PartyHandler(PartyModel pModel) {
		this.pModel = pModel;
	}
	
	public Party createParty(EntityPlayer leader) {
		Party p = new Party(pModel.nextPartyId, leader);
		pModel.nextPartyId++;
		leader.partyId = p.id;
		pModel.parties.put(p.id, p);
		return p;
	}
	
	public Party inviteParty(EntityPlayer inviter, EntityPlayer invitee) {
		Party p = getPartyById(inviter.partyId);
		invitee.partyInvId = p.id;
		p.invites++;
		return p;
	}
	
	public Party joinParty(EntityPlayer player) {
		// Get the party to join
		Party p = getPartyById(player.partyInvId);
		p.members.add(player);
		p.invites--;
		player.partyId = p.id;
		player.partyInvId = 0;
		return p;
	}
	
	public Party declineParty(EntityPlayer player) {
		Party p = getPartyById(player.partyInvId);
		player.partyInvId = 0;
		p.invites--;
		return p;
	}
	
	public Party getPartyById(int id) {
		return pModel.parties.get(id);
	}
}
