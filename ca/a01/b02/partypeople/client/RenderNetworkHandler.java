package ca.a01.b02.partypeople.client;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

import ca.a01.b02.partypeople.PartyPlayer;
import ca.a01.b02.partypeople.client.renderer.EntityNameTag;

public class RenderNetworkHandler {

    private static RenderNetworkHandler instance = new RenderNetworkHandler();
    private final RenderData            rData;

    public RenderNetworkHandler() {
        this.rData = RenderData.instance();
    }

    public static RenderNetworkHandler instance() {
        return instance;
    }

    public void joinParty(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            this.rData.partyplayers.put(p.username, p);
            if(!p.username.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.username))
            {
            	EntityNameTag nameTag = new EntityNameTag(p);
            	Minecraft.getMinecraft().theWorld.spawnEntityInWorld(nameTag);
            	this.rData.nametags.put(p.username, nameTag);
            }
        }
        System.out.println("I have joined a party!");
    }

    public void leaveParty() {
        this.rData.partyplayers.clear();
        for(EntityNameTag nameTag : this.rData.nametags.values())
        {
        	Minecraft.getMinecraft().theWorld.removeEntity(nameTag);
        }
        this.rData.nametags.clear();
        System.out.println("I have left a party!");
    }

    public void playerPartyJoin(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            this.rData.partyplayers.put(p.username, p);
            System.out.println("Player " + p.username + " joined!");
            // TODO: WHAT DO WE SAY TO FIXING COPY PASTA CODE: not today! 
            if(!p.username.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.username))
            {
	            EntityNameTag nameTag = new EntityNameTag(p);
	            Minecraft.getMinecraft().theWorld.spawnEntityInWorld(nameTag);
	            this.rData.nametags.put(p.username, nameTag);
            }
        }
    }

    public void playerPartyUpdate(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            System.out.println("Got update for player " + p.username);
            PartyPlayer pp = rData.partyplayers.get(p.username);
            pp.posX = p.posX;
            pp.posY = p.posY;
            pp.posZ = p.posZ;
            pp.dimension = p.dimension;
            pp.health = p.health;
            pp.entityId = p.entityId;

            // update entity position with new player position data!
            this.rData.nametags.get(p.username).updatePosition();
        }
    }

    public void playerPartyLeave(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            this.rData.partyplayers.remove(p.username);
            System.out.println("Player " + p.username + " left!");
            EntityNameTag nameTag = this.rData.nametags.get(p.username);
            Minecraft.getMinecraft().theWorld.removeEntity(nameTag);
            this.rData.nametags.remove(nameTag);
        }
    }
}
