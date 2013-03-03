package ca.a01.b02.partypeople.client;

import ca.a01.b02.partypeople.CommonProxy;
import ca.a01.b02.partypeople.PartyModel;
import ca.a01.b02.partypeople.client.renderer.RenderTickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerHandlers(PartyModel pModel) {
        super.registerHandlers(pModel);
        RenderTickHandler renderTickHandler = new RenderTickHandler();
        TickRegistry.registerTickHandler(renderTickHandler, Side.CLIENT);
    }
}
