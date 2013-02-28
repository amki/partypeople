package ca.a01.b02.partypeople;

import java.util.HashSet;

import net.minecraft.entity.player.EntityPlayer;

public class Party {

    public int                   id;
    public int                   invites = 0;
    public EntityPlayer          leader;
    public HashSet<EntityPlayer> members;

    public Party(int id, EntityPlayer leader) {
        this.id = id;
        this.leader = leader;
    }
}
