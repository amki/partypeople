package ca.a01.b02.partypeople.client.renderer;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RenderTickHandler implements ITickHandler {
	private PartyHudRenderer hudRenderer;
	
	public RenderTickHandler() {
		hudRenderer = new PartyHudRenderer();
	}
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		if (player != null) {
			hudRenderer.drawHud(10, 15);
		}
	}
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}
	public String getLabel() {
		return "PartyPeople: Render Tick";
	}
}
