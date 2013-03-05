package ca.a01.b02.partypeople.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LivingUpdateHandler {

	public LivingUpdateHandler() {
		// TODO Auto-generated constructor stub
	}

	@ForgeSubscribe
	public void onHurtEvent(LivingUpdateEvent event) {
		if(event.entity instanceof EntityPlayer ) {
			EntityPlayer player = (EntityPlayer)event.entity;
			RenderData rData = RenderData.instance();
			if(player.username == Minecraft.getMinecraft().thePlayer.username) {
				if(rData.partyplayers.get(player.username) != null)
					rData.partyplayers.get(player.username).health = player.getHealth();
			}
		}
		
	}
}
