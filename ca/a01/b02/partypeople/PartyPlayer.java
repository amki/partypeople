package ca.a01.b02.partypeople;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;

public class PartyPlayer {

    public int     entityId;
    public String  username;
    public int     health;
    public int     dimension;
    public double  posX;
    public double  posY;
    public double  posZ;

    // SERVER - not transferred fields
    public int     partyId;
    public long    lastSent  = 0;
    public boolean isChanged = false;

    public PartyPlayer(int partyId, EntityPlayer p) {
        this.partyId = partyId;
        this.entityId = p.entityId;
        this.username = p.username;
        this.health = p.getHealth();
        this.dimension = p.dimension;
        this.posX = p.posX;
        this.posY = p.posY;
        this.posZ = p.posZ;
    }

    public PartyPlayer(DataInputStream dis) throws IOException {
        this.entityId = dis.readInt();
        this.username = dis.readUTF();
        this.health = dis.readInt();
        this.dimension = dis.readInt();
        this.posX = dis.readDouble();
        this.posY = dis.readDouble();
        this.posZ = dis.readDouble();
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.entityId);
        dos.writeUTF(this.username);
        dos.writeInt(this.health);
        dos.writeInt(this.dimension);
        dos.writeDouble(this.posX);
        dos.writeDouble(this.posY);
        dos.writeDouble(this.posZ);
        dos.flush();

        return baos.toByteArray();
    }
}
