package ca.a01.b02.partypeople.client.renderer;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.opengl.GL11;

import ca.a01.b02.partypeople.PartyPlayer;
import ca.a01.b02.partypeople.client.RenderData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

public class RenderPlayerTag extends Render {
	Minecraft mc;
	RenderData rawData;
	double far = 1.0D;
	double _d = 1.0D;
	public RenderPlayerTag() {
		this.mc = Minecraft.getMinecraft();
		rawData = RenderData.instance();
	    this.far = ((512 >> this.mc.gameSettings.renderDistance) * 0.8D);
	    this._d = (1.0D / (256 >> this.mc.gameSettings.renderDistance));
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		EntityNameTag entity = (EntityNameTag)var1;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String dstString = df.format(entity.getDistanceSqToEntity(this.renderManager.livingPlayer));
        FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
        float var13 = 1.6F;
        float var14 = 0.016666668F * var13;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2 + 0.0F, (float)var4 + 2.3F, (float)var6);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-var14, -var14, var14);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tesselator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tesselator.startDrawingQuads();
        int stringWidth = fontRenderer.getStringWidth(entity.p.username) / 2;
        tesselator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tesselator.addVertex((double)(-stringWidth - 1), (double)(-1), 0.0D);
        tesselator.addVertex((double)(-stringWidth - 1), (double)(8), 0.0D);
        tesselator.addVertex((double)(stringWidth + 1), (double)(8), 0.0D);
        tesselator.addVertex((double)(stringWidth + 1), (double)(-1), 0.0D);
        stringWidth = fontRenderer.getStringWidth(dstString) / 2;
        tesselator.addVertex((double)(-stringWidth -1), (double)(8), 0.0D);
        tesselator.addVertex((double)(-stringWidth - 1), (double)(17), 0.0D);
        tesselator.addVertex((double)(stringWidth + 1), (double)(17), 0.0D);
        tesselator.addVertex((double)(stringWidth + 1), (double)(8), 0.0D);
        tesselator.draw();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        fontRenderer.drawString(entity.p.username, -fontRenderer.getStringWidth(entity.p.username) / 2, 0, 553648127);
        fontRenderer.drawString(dstString, -fontRenderer.getStringWidth(dstString) / 2, 9, 553648127);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        fontRenderer.drawString(entity.p.username, -fontRenderer.getStringWidth(entity.p.username) / 2, 0, -1);
        fontRenderer.drawString(dstString, -fontRenderer.getStringWidth(dstString) / 2, 9, -1);
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();

	}
}


