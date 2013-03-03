package ca.a01.b02.partypeople.client;

import net.minecraft.client.Minecraft;
import ca.a01.b02.partypeople.CommonProxy;
import ca.a01.b02.partypeople.client.renderer.EntityNameTag;
import ca.a01.b02.partypeople.client.renderer.RenderPlayerTag;
import ca.a01.b02.partypeople.client.renderer.RenderTickHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        RenderingRegistry.registerEntityRenderingHandler(EntityNameTag.class, new RenderPlayerTag(Minecraft.getMinecraft()));
        RenderTickHandler renderTickHandler = new RenderTickHandler();
        TickRegistry.registerTickHandler(renderTickHandler, Side.CLIENT);
    }
}
