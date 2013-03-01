package ca.a01.b02.partypeople.server;

import java.util.EnumSet;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import ca.a01.b02.partypeople.PartyPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerTickHandler implements ITickHandler {

    private final HashMap<String, PartyPlayer> players = new HashMap<String, PartyPlayer>();

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (tickData[0] instanceof EntityPlayer) {
            if (!this.players.containsKey(((EntityPlayer) tickData[0]).username)) {
                PartyPlayer pp = new PartyPlayer((EntityPlayer) tickData[0]);
                this.players.put(pp.username, pp);
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if (tickData[0] instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) tickData[0];
            PartyPlayer pp = this.players.get(p.username);
            if (Math.abs(p.posX - pp.posX) > 0.1) {
                System.out.println("X changed! " + Math.abs(p.posX - pp.posX));
                pp.posX = p.posX;
            }
            if (Math.abs(p.posY - pp.posY) > 0.1) {
                System.out.println("Y changed!" + Math.abs(p.posY - pp.posY));
                pp.posY = p.posY;
            }
            if (Math.abs(p.posZ - pp.posZ) > 0.1) {
                System.out.println("Z changed!" + Math.abs(p.posZ - pp.posZ));
                pp.posZ = p.posZ;
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "PartyPeople: Player Tick";
    }

}
