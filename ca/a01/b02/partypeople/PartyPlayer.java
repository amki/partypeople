package ca.a01.b02.partypeople;

import net.minecraft.entity.player.EntityPlayer;

public class PartyPlayer {

    public int    entityId;
    public String username;
    public double posX;
    public double posY;
    public double posZ;

    public PartyPlayer(EntityPlayer p) {
        this.entityId = p.entityId;
        this.username = p.username;
        this.posX = p.posX;
        this.posY = p.posY;
        this.posZ = p.posZ;
    }
}
