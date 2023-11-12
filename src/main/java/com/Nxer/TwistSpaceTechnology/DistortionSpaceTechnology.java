package com.Nxer.TwistSpaceTechnology;

import com.Nxer.TwistSpaceTechnology.nei.NEIHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Nxer.TwistSpaceTechnology.devTools.PathHelper;
import com.Nxer.TwistSpaceTechnology.loader.MachineLoader;
import com.Nxer.TwistSpaceTechnology.loader.MaterialLoader;
import com.Nxer.TwistSpaceTechnology.loader.RecipeLoader;
import com.Nxer.TwistSpaceTechnology.util.TextHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    dependencies = "required-after:IC2; " + "required-after:gregtech; "
        + "required-after:bartworks; "
        + "required-after:tectech; "
        + "before:miscutils; "
        + "after:dreamcraft;",
    acceptedMinecraftVersions = "[1.7.10]")
public class DistortionSpaceTechnology {

    /**
     * <li>The signal of whether in Development Mode.
     * <li>Keep care to set 'false' when dev complete.
     */
    public static final boolean isInDevMode = false;

    /**
     * The absolute Path of your workspace/resources folder.
     * It will be replaced by {@link PathHelper#initResourceAbsolutePath(boolean)}.
     * If it not work correctly, please operate it manually and disable
     * the{@link PathHelper#initResourceAbsolutePath(boolean)}.
     */
    public static String DevResource = "";

    public static final String MODID = Tags.MODID;
    public static final String MOD_ID = Tags.MODID;
    public static final String MOD_NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;

    /**
     * If you need send a message to the Log, call {@link DistortionSpaceTechnology#LOG#info(String message)} .
     */
    public static final Logger LOG = LogManager.getLogger(Tags.MODID);

    @Mod.Instance
    public static DistortionSpaceTechnology instance;

    @SidedProxy(
        clientSide = "com.Nxer.TwistSpaceTechnology.ClientProxy",
        serverSide = "com.Nxer.TwistSpaceTechnology.CommonProxy")
    public static CommonProxy proxy;

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //
        PathHelper.initResourceAbsolutePath(isInDevMode);
        TextHandler.initLangMap(isInDevMode);

        proxy.preInit(event);
        MaterialLoader.load();// Load MaterialPool
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        MachineLoader.loadMachines();// Load Machines
        NEIHandler.IMCSender();// NEI reg

    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        TextHandler.serializeLangMap(isInDevMode);

        // DistortionSpaceTechnology.LOG.info("test GT.getResourcePath : " + GregTech.getResourcePath("testing"));
    }

    @Mod.EventHandler
    public void completeInit(FMLLoadCompleteEvent event) {
        DistortionSpaceTechnology.LOG.info("Start Complete Init.");
        RecipeLoader.loadRecipes();// Load Recipes

        // reflect

        //

    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
