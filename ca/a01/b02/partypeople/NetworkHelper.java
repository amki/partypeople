package ca.a01.b02.partypeople;

import java.io.ByteArrayOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class NetworkHelper {

    public static void sendToPlayer(ByteArrayOutputStream baos, EntityPlayer player) {
        Packet250CustomPayload pack = new Packet250CustomPayload();
        pack.channel = PartyModel.CHANNEL_PPPARTY;
        pack.data = baos.toByteArray();
        pack.length = baos.size();
        PacketDispatcher.sendPacketToPlayer(pack, (Player) player);
    }

    public static void broadcastToParty(ByteArrayOutputStream baos, Party party, EntityPlayerMP player) {
        // Send packet to party leader
        EntityPlayer leader = player.getServerForPlayer().getPlayerEntityByName(party.leader);
        if (leader != null) {
            sendToPlayer(baos, leader);
        }
        // Send to all members
        for (String member : party.members) {
            EntityPlayer m = player.getServerForPlayer().getPlayerEntityByName(member);
            sendToPlayer(baos, m);
        }
    }

    public static void broadcastToPartyExceptPlayer(ByteArrayOutputStream baos, Party party, EntityPlayerMP player) {
        if (party.leader != player.username) {
            // Send packet to party leader
            EntityPlayer leader = player.getServerForPlayer().getPlayerEntityByName(party.leader);
            if (leader != null) {
                sendToPlayer(baos, leader);
            }
        }
        // Send to all members
        for (String member : party.members) {
            if (member != player.username) {
                EntityPlayer m = player.getServerForPlayer().getPlayerEntityByName(member);
                sendToPlayer(baos, m);
            }
        }
    }
}
