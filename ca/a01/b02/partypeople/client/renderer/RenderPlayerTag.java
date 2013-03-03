package ca.a01.b02.partypeople.client.renderer;

import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

public class RenderPlayerTag extends Render {
	final Minecraft mc;
	ArrayList<VisiblePlayerPosition> positions;
	double far = 1.0D;
	double _d = 1.0D;
	public RenderPlayerTag(Minecraft mc) {
		this.mc = mc;
		positions = new ArrayList();
	      this.far = ((512 >> this.mc.gameSettings.renderDistance) * 0.8D);
	      this._d = (1.0D / (256 >> this.mc.gameSettings.renderDistance));
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		EntityNameTag entity = (EntityNameTag)var1;
		
		// prepare list
		
		for(PlayerPosition pos : )
		{
			positions.add(new VisiblePlayerPosition(pos, 1));
		}
		
		if(positions.isEmpty())
			return;
		
		Collections.sort(positions);
		this.mc.entityRenderer.disableLightmap(0.0D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		for(VisiblePlayerPosition v : positions)
			draw(v, var8, var9);

	}
	private void draw(VisiblePlayerPosition v, float var8, float var9) {
		float alpha = (float)Math.max(0.0D, 1.0D - v.distance * this._d);
	    FontRenderer fontrenderer = getFontRendererFromRenderManager();
	    GL11.glPushMatrix();
	    StringBuilder sb = new StringBuilder();
	    sb.append(v.name);
	    sb.append(String.format("[%1.2fm]", new Object[] { Double.valueOf(v.distance) }));

	    String str = sb.toString();
	    double scale = (v.dl * 0.1D + 1.0D) * 0.02666666666666667D;
	    int slideY = -16;
	    GL11.glTranslated(v.dx, v.dy, v.dz);

	    GL11.glRotatef(-this.renderManager.playerViewX, 0.0F, 1.0F, 0.0F);
	    GL11.glRotatef(this.mc.gameSettings.thirdPersonView == 2 ? -this.renderManager.playerViewY : this.renderManager.playerViewY, 1.0F, 0.0F, 0.0F);
	    GL11.glScaled(-scale, -scale, scale);
	    GL11.glEnable(3042);
	    GL11.glBlendFunc(770, 771);
	    Tessellator tessellator = Tessellator.instance;

	    /*
	    if (rm.getMarkerIcon())
	    {
	      GL11.glEnable(3553);
	      GL11.glDisable(2929);
	      GL11.glDepthMask(false);
	      Waypoint.FILE[w.type].bind();
	      tessellator.b();
	      tessellator.a(w.red, w.green, w.blue, 0.4F);
	      tessellator.a(-8.0D, -8.0D, 0.0D, 0.0D, 0.0D);
	      tessellator.a(-8.0D, 8.0D, 0.0D, 0.0D, 1.0D);
	      tessellator.a(8.0D, 8.0D, 0.0D, 1.0D, 1.0D);
	      tessellator.a(8.0D, -8.0D, 0.0D, 1.0D, 0.0D);
	      tessellator.a();
	      GL11.glEnable(2929);
	      GL11.glDepthMask(true);
	      tessellator.b();
	      tessellator.a(w.red, w.green, w.blue, alpha);
	      tessellator.a(-8.0D, -8.0D, 0.0D, 0.0D, 0.0D);
	      tessellator.a(-8.0D, 8.0D, 0.0D, 0.0D, 1.0D);
	      tessellator.a(8.0D, 8.0D, 0.0D, 1.0D, 1.0D);
	      tessellator.a(8.0D, -8.0D, 0.0D, 1.0D, 0.0D);
	      tessellator.a();
	    }*/

	    int j = fontrenderer.getStringWidth(str) >> 1;

	    if (j != 0)
	    {
	      GL11.glDisable(3553);
	      GL11.glDisable(2929);
	      GL11.glDepthMask(false);
	      tessellator.startDrawingQuads();
	      tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.6275F);
	      tessellator.addVertex(-j - 1, slideY - 1, 0.0D);
	      tessellator.addVertex(-j - 1, slideY + 8, 0.0D);
	      tessellator.addVertex(j + 1, slideY + 8, 0.0D);
	      tessellator.addVertex(j + 1, slideY - 1, 0.0D);
	      tessellator.draw();
	      GL11.glEnable(3553);
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	      fontrenderer.drawString(str, -j, slideY, 1627389951);
	      GL11.glEnable(2929);
	      GL11.glDepthMask(true);
	      int a = (int)(255.0F * alpha);

	      if (a != 0)
	      {
	        fontrenderer.drawString(str, -j, slideY, 16777215);
	      }
	    }

	    GL11.glDisable(3042);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    GL11.glEnable(3553);
	    GL11.glPopMatrix();	
		
	}
	/*
	    float statVar1 = 1.6F;
        float statVar2 = 0.016666668F * statVar1;
        FontRenderer fontRenderer = renderManager.getFontRenderer();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2 + 0.0F, (float)var4 + 2.3F, (float)var6);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-statVar2, -statVar2, statVar2);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef(0.0F, 0.25F / statVar2, 0.0F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator var15 = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        var15.startDrawingQuads();
        int stringwidth = fontRenderer.getStringWidth(entity.playerName) / 2;
        var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        var15.addVertex((double)(-stringwidth - 1), -1.0D, 0.0D);
        var15.addVertex((double)(-stringwidth - 1), 8.0D, 0.0D);
        var15.addVertex((double)(stringwidth + 1), 8.0D, 0.0D);
        var15.addVertex((double)(stringwidth + 1), -1.0D, 0.0D);
        var15.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        fontRenderer.drawString(entity.playerName, -fontRenderer.getStringWidth(entity.playerName) / 2, 0, 553648127);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
	 */
	private class VisiblePlayerPosition extends PlayerPosition
	implements Comparable
	{
	    double dx;
	    double dy;
	    double dz;
	    double dl;
	    double distance;
	    
		public VisiblePlayerPosition(PlayerPosition pos, double dscale) {
			
			super(pos);
			this.dx = (pos.x * dscale - renderManager.renderPosX + 0.5D);
			this.dy = (pos.y - renderManager.renderPosY + 0.5D);
			this.dz = (pos.z * dscale - renderManager.renderPosZ + 0.5D);
			this.dl = (this.distance = Math.sqrt(this.dx * this.dx + this.dy * this.dy + this.dz * this.dz));
			double d;
			if (this.dl > RenderPlayerTag.this.far)
			{
				d = RenderPlayerTag.this.far / this.dl;
			    this.dx *= d;
			    this.dy *= d;
			    this.dz *= d;
			    this.dl = RenderPlayerTag.this.far;
			}
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		
	}
}


