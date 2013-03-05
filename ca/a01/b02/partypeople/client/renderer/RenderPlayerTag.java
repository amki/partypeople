package ca.a01.b02.partypeople.client.renderer;

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
	    System.out.println("created new RenderPlayerTag");
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		EntityNameTag entity = (EntityNameTag)var1;
		System.out.println("rendering entity with name: "+entity.p.username+" at x: "+entity.p.posX+" y: "+entity.p.posY+" z: "+entity.p.posZ);
        FontRenderer var12 = this.getFontRendererFromRenderManager();
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
        Tessellator var15 = Tessellator.instance;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        var15.startDrawingQuads();
        int var17 = var12.getStringWidth(entity.p.username) / 2;
        var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        var15.addVertex((double)(-var17 - 1), (double)(-1), 0.0D);
        var15.addVertex((double)(-var17 - 1), (double)(8), 0.0D);
        var15.addVertex((double)(var17 + 1), (double)(8), 0.0D);
        var15.addVertex((double)(var17 + 1), (double)(-1), 0.0D);
        var15.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        var12.drawString(entity.p.username, -var12.getStringWidth(entity.p.username) / 2, 0, 553648127);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        var12.drawString(entity.p.username, -var12.getStringWidth(entity.p.username) / 2, 0, -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();

	}
}


