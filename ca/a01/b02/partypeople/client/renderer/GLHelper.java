package ca.a01.b02.partypeople.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;

public class GLHelper {
	public static void drawString(String s, double x, double y) {
		RenderHelper.disableStandardItemLighting();
		getFontRenderer().drawStringWithShadow(s, (int) x, (int) y, new Colour(1, 1, 1, 1).getInt());
	}
	
	public static FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}
}
