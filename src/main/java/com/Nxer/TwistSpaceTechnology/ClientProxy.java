package com.Nxer.TwistSpaceTechnology;

import net.minecraftforge.common.MinecraftForge;

import com.Nxer.TwistSpaceTechnology.client.render.ArtificialStarRender;
import com.Nxer.TwistSpaceTechnology.system.ItemCooldown.CooldownEventHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        new ArtificialStarRender();
        MinecraftForge.EVENT_BUS.register(new CooldownEventHandler());// load cooldown HUD
    }
}
