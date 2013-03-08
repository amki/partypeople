package ca.a01.b02.partypeople.client.renderer;

import java.util.Vector;

import ca.a01.b02.partypeople.PartyPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityNameTag extends Entity {
	public PartyPlayer p;
	public int playerDistance;
	public EntityNameTag(PartyPlayer p) {
		super(Minecraft.getMinecraft().theWorld);
		this.p = p;
		setSize(0.0F, 0.0F);
		setPosition(p.posX, p.posY, p.posZ);
		this.ignoreFrustumCheck = true;
	}

	public void updatePosition() {
		setPosition(p.posX, p.posY, p.posZ);
	}
	
	@Override
	public boolean isInRangeToRenderVec3D(net.minecraft.util.Vec3 par1Vec3) {
		// Todo: find a good way to render the EntityNameTag when out of visible range
		return true;
	};
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		setPosition(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub

	}

}
