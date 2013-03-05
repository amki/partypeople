package ca.a01.b02.partypeople.client.renderer;

import ca.a01.b02.partypeople.PartyPlayer;
import ca.a01.b02.partypeople.client.RenderData;
import net.minecraft.client.Minecraft;

public class PartyHudRenderer {
	RenderData rData = RenderData.instance();
	private final static int HUD_SPACING = 25;
	public PartyHudRenderer() {
		// TODO Auto-generated constructor stub
	}
	
	public void drawHud(int x, int y) {
		for(PartyPlayer p : rData.partyplayers.values()) {
			drawPlayerFrame(p, x, y);
			y += HUD_SPACING;
		}
	}
	
	private void drawPlayerFrame(PartyPlayer p, int x, int y) {
		GLHelper.drawString(p.username, x, y);
		drawHealthBar(p, x, y+7);
		
	}
	private void drawHealthBar(PartyPlayer p, int x, int y) {
		int numhearts = (int) Math.floor(p.health/2);
		System.out.println(p.health);
		// draw empty hearts
		for(int i = 0; i < 10; i++) {
			GLHelper.drawIcon("/gui/icons.png", x+(i*12), y, 16, 0,9,9);
		}
		
		// draw full hearts
		int i;
		for(i = 0; i < numhearts; i++) {
			GLHelper.drawIcon("/gui/icons.png", x+(i*10), y, 52, 0,9,9);
		}
		
		if(p.health%2 != 0)
		{
			GLHelper.drawIcon("/gui/icons.png", x+(i*10), y, 61, 0,9,9);
		}
		
	}
}
