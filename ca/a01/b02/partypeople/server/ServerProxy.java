package ca.a01.b02.partypeople.server;

import ca.a01.b02.partypeople.CommonProxy;
import ca.a01.b02.partypeople.PartyModel;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ServerProxy extends CommonProxy {

    @Override
    public void registerHandlers(PartyModel pModel) {
        super.registerHandlers(pModel);
        PlayerTickHandler playerTickHandler = new PlayerTickHandler(pModel);
        TickRegistry.registerTickHandler(playerTickHandler, Side.SERVER);
    }
}
