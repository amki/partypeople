package ca.a01.b02.partypeople;

import java.io.ByteArrayOutputStream;

import net.minecraft.entity.player.EntityPlayer;
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
}
