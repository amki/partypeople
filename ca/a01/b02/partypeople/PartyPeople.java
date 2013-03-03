package ca.a01.b02.partypeople;

import ca.a01.b02.partypeople.client.RenderData;
import ca.a01.b02.partypeople.server.CommandPP;
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

@Mod(modid = "PartyPeople", name = "PartyPeople", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { "PPParty" }, packetHandler = PacketHandler.class)
public class PartyPeople {

    private final PartyModel  pModel = new PartyModel();
    private final RenderData  rData  = new RenderData();

    // The instance of your mod that Forge uses.
    @Instance("PartyPeople")
    public static PartyPeople instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "ca.a01.b02.partypeople.client.ClientProxy", serverSide = "ca.a01.b02.partypeople.server.ServerProxy")
    public static CommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Stub Method
    }

    @Init
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();
        proxy.registerHandlers(this.pModel);
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // Stub Method
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandPP(this.pModel));
    }
}
