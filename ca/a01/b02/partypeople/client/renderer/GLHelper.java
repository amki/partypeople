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
	public static void drawIcon(String texName, double x, double y, int horOffset, int vertOffset, int width, int height) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(texName));
		drawTexturedModalRect((int)x, (int)y, horOffset, vertOffset, width, height);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glPopMatrix();
	}
	
	public static void draw3dLocation(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F,
				0.0F);
		GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glRasterPos3d(x, y, z);
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
	
	public static void drawString(String string, double x, double y, double z) {
		RenderManager renderManager = RenderManager.instance;
		System.out.println("rendering string: "+string+" at x: "+String.valueOf(x)+" y: "+String.valueOf(y)+" z: "+String.valueOf(z));
		renderManager.cacheActiveRenderInfo(Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().renderEngine, getFontRenderer(), Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().gameSettings, 0);
        FontRenderer fontRenderer = getFontRenderer();
        float var13 = 1.6F;
        float var14 = 0.016666668F * var13;
        GL11.glPushMatrix();
        DoubleBuffer n = ByteBuffer.allocateDirect(16*8).order(ByteOrder.nativeOrder()).asDoubleBuffer();
        GL11.glMultMatrix(n);
        GL11.glTranslatef((float)x + 0.0F, (float)y + 2.3F, (float)z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-var14, -var14, var14);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tess = Tessellator.instance;
        byte var16 = 0;

        if (string.equals("deadmau5"))
        {
            var16 = -10;
        }

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tess.startDrawingQuads();
        int var17 = fontRenderer.getStringWidth(string) / 2;
        tess.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tess.addVertex((double)(-var17 - 1), (double)(-1 + var16), 0.0D);
        tess.addVertex((double)(-var17 - 1), (double)(8 + var16), 0.0D);
        tess.addVertex((double)(var17 + 1), (double)(8 + var16), 0.0D);
        tess.addVertex((double)(var17 + 1), (double)(-1 + var16), 0.0D);
        tess.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        fontRenderer.drawString(string, -fontRenderer.getStringWidth(string) / 2, var16, 553648127);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        fontRenderer.drawString(string, -fontRenderer.getStringWidth(string) / 2, var16, -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
	}

	
	public static FontRenderer getFontRenderer() {
		return Minecraft.getMinecraft().fontRenderer;
	}
}
