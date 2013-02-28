package a01.b02.partypeople;

import a01.b02.partypeople.server.CommandPPInv;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="PartyPeople", name="PartyPeople", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class PartyPeople {

    // The instance of your mod that Forge uses.
    @Instance("PartyPeople")
    public static PartyPeople instance;
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="a01.b02.partypeople.client.ClientProxy", serverSide="a01.b02.partypeople.server.ServerProxy")
    public static CommonProxy proxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
            // Stub Method
    }
    
    @Init
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderers();
            proxy.registerHandlers();
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
    
    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandPPInv(this));
    }
}
