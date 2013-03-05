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
        }
        System.out.println("I have joined a party!");
        Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new EntityNameTag(Minecraft.getMinecraft().theWorld));
    }

    public void leaveParty() {
        this.rData.partyplayers.clear();
        System.out.println("I have left a party!");
    }

    public void playerPartyJoin(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            this.rData.partyplayers.put(p.username, p);
            System.out.println("Player " + p.username + " joined!");
        }
    }

    public void playerPartyUpdate(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            System.out.println("Got update for player " + p.username);
        }
    }

    public void playerPartyLeave(ArrayList<PartyPlayer> players) {
        for (PartyPlayer p : players) {
            this.rData.partyplayers.remove(p.username);
            System.out.println("Player " + p.username + " left!");
        }
    }
}
