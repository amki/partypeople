package ca.a01.b02.partypeople.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import ca.a01.b02.partypeople.NetworkHelper;
import ca.a01.b02.partypeople.Party;
import ca.a01.b02.partypeople.PartyHandler;
import ca.a01.b02.partypeople.PartyModel;
import ca.a01.b02.partypeople.PartyPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerTickHandler implements ITickHandler {

    private final PartyModel pModel;

    public PlayerTickHandler(PartyModel pModel) {
        this.pModel = pModel;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if (tickData[0] instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) tickData[0];
            PartyPlayer pp = this.pModel.partyplayer.get(p.username);
            // If this player is not in a party disregard him.
            if (pp == null) {
                return;
            }
            boolean isChanged = false;
            if (Math.abs(p.posX - pp.posX) > 0.1) {
                isChanged = true;
                pp.posX = p.posX;
            }
            if (Math.abs(p.posY - pp.posY) > 0.1) {
                isChanged = true;
                pp.posY = p.posY;
            }
            if (Math.abs(p.posZ - pp.posZ) > 0.1) {
                isChanged = true;
                pp.posZ = p.posZ;
            }
            if (isChanged) {
                this.sendUpdatePacket(p, pp);
            }
        }
    }

    private void sendUpdatePacket(EntityPlayer player, PartyPlayer pp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeByte(this.pModel.OPCODE_PARTY_UPDATE);
            dos.writeByte(this.pModel.SUBCODE_PLAYER_UPDATE);
            // There is always one player in this packet...
            dos.writeByte(1);
            dos.write(pp.serialize());
            baos.flush();
            Party party = PartyHandler.getPartyByPlayer(this.pModel, (EntityPlayerMP) player);
            NetworkHelper.broadcastToPartyExceptPlayer(baos, party, (EntityPlayerMP) player);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
