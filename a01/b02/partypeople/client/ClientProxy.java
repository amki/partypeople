package a01.b02.partypeople.client;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import a01.b02.partypeople.CommonProxy;
import a01.b02.partypeople.client.renderer.RenderTickHandler;

public class ClientProxy extends CommonProxy {
@Override
public void registerHandlers() {
	super.registerHandlers();
	RenderTickHandler renderTickHandler = new RenderTickHandler();
	TickRegistry.registerTickHandler(renderTickHandler, Side.CLIENT);
}
}
