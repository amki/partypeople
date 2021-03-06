package ca.a01.b02.partypeople.client.renderer;

import org.lwjgl.opengl.GL11;

import ca.a01.b02.partypeople.PartyPlayer;
import ca.a01.b02.partypeople.client.RenderData;
import net.minecraft.client.Minecraft;

public class PartyHudRenderer {
	RenderData rData = RenderData.instance();
	private final static int HUD_SPACING = 25;
	private final static int HUD_HEALTH_SPACING = 9;
	public PartyHudRenderer() {
		// TODO Auto-generated constructor stub
	}
	
	public void drawHud(int x, int y) {
		// Todo: find out what is fucking up my shit when mixing drawString with drawIcon...
		int dy = y;
		for(PartyPlayer p : rData.partyplayers.values()) {
			GLHelper.drawString(p.username, x, dy);
			dy += HUD_SPACING;
		}
		dy = y;
		for(PartyPlayer p : rData.partyplayers.values()) {
			drawHealthBar(p, x-2, dy+8);
			dy += HUD_SPACING;
		}
	}
	
	private void drawPlayerFrame(PartyPlayer p, int x, int y) {
		GLHelper.drawString(p.username, x, y);
		drawHealthBar(p, x-2, y+8);
	}
	private void drawHealthBar(PartyPlayer p, int x, int y) {
		int numhearts = (int) Math.floor(p.health/2);
		// draw empty hearts
		GLHelper.startDrawIcon("/gui/icons.png");
		for(int i = 0; i < 10; i++) {
			GLHelper.drawTexturedModalRect(x+(i*HUD_HEALTH_SPACING), y, 16, 0,9,9);
		}
		
		// draw full hearts
		int i;
		for(i = 0; i < numhearts; i++) {
			GLHelper.drawTexturedModalRect(x+(i*HUD_HEALTH_SPACING), y, 52, 0,9,9);
		}
		
		if(p.health%2 != 0)
		{
			GLHelper.drawTexturedModalRect(x+(i*HUD_HEALTH_SPACING), y, 61, 0,9,9);
		}
		GLHelper.endDrawIcon();
		
	}
}
