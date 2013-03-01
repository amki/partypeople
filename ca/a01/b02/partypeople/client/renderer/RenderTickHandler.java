package ca.a01.b02.partypeople.client.renderer;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RenderTickHandler implements ITickHandler {
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		if (player != null) {
		GLHelper.drawString("Krebskopf", 10, 15);
		GLHelper.drawIcon("/gui/icons.png", 10, 25, 52, 0,9,9);
		}
	}
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}
	public String getLabel() {
		return "PartyPeople: Render Tick";
	}
}
