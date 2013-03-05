package ca.a01.b02.partypeople.client.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.logging.Logger;

import javax.swing.DebugGraphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.ForgeHooksClient;

public class GLHelper {
	protected static float zLevel = 0.0F;
	private static RenderManager renderManager;
	
    public static void drawTexturedModalRect(int par1, int par2, int horizontalOffset, int verticalOffset, int width, int height)
    {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertexWithUV((double)(par1 + 0), (double)(par2 + height), (double)zLevel, (double)((float)(horizontalOffset + 0) * var7), (double)((float)(verticalOffset + height) * var8));
        tess.addVertexWithUV((double)(par1 + width), (double)(par2 + height), (double)zLevel, (double)((float)(horizontalOffset + width) * var7), (double)((float)(verticalOffset + height) * var8));
        tess.addVertexWithUV((double)(par1 + width), (double)(par2 + 0), (double)zLevel, (double)((float)(horizontalOffset + width) * var7), (double)((float)(verticalOffset + 0) * var8));
        tess.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)zLevel, (double)((float)(horizontalOffset + 0) * var7), (double)((float)(verticalOffset + 0) * var8));
        tess.draw();
    }
	
	public static void drawString(String s, double x, double y) {
		RenderHelper.disableStandardItemLighting();
		getFontRenderer().drawStringWithShadow(s, (int) x, (int) y, new Colour(1, 1, 1, 1).getInt());
		RenderHelper.enableStandardItemLighting();
	}
	
	/***
	 * loads a texture from file texName and draws part of that image, which is defined by horOffset and vertOffset with size width and height
	 * at positions x and y
	 * @param texName file name of texture(tested with .png)
	 * @param x position to render on screen
	 * @param y position to render on screen
	 * @param horOffset pixel offset in .png
	 * @param vertOffset pixel offset in .png
	 * @param width size of the partial image in the .png file
	 * @param height size of the partial image in the .png file
	 */
	@Deprecated
	public static void drawIcon(String texName, double x, double y, int horOffset, int vertOffset, int width, int height) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(texName));
		drawTexturedModalRect((int)x, (int)y, horOffset, vertOffset, width, height);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	public static void startDrawIcon(String texName) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(texName));
	}
	
	public static void endDrawIcon() {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glPopMatrix();
	}
	
	public static FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}
}
