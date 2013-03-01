package ca.a01.b02.partypeople.server;

import ca.a01.b02.partypeople.CommonProxy;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ServerProxy extends CommonProxy {

    @Override
    public void registerHandlers() {
        super.registerHandlers();
        PlayerTickHandler playerTickHandler = new PlayerTickHandler();
        TickRegistry.registerTickHandler(playerTickHandler, Side.SERVER);
    }
}
