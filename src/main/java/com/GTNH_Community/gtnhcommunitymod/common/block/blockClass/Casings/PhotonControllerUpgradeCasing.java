package com.GTNH_Community.gtnhcommunitymod.common.block.blockClass.Casings;

import static com.GTNH_Community.gtnhcommunitymod.common.GTCMCreativeTabs.tabGTCMGeneralTab;
import static com.GTNH_Community.gtnhcommunitymod.common.block.blockClass.BlockStaticDataClientOnly.iconsBlockPhotonControllerUpgradeMap;
import static com.GTNH_Community.gtnhcommunitymod.common.block.blockList01.PhotonControllerUpgrade;
import static com.GTNH_Community.gtnhcommunitymod.util.TextHandler.texter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.GTNH_Community.gtnhcommunitymod.common.block.blockClass.BlockBase01;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTech_API;

public class PhotonControllerUpgradeCasing extends BlockBase01 {

    // region Constructors

    public PhotonControllerUpgradeCasing() {
        this.setHardness(9.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("wrench", 1);
        this.setCreativeTab(tabGTCMGeneralTab);
        PhotonControllerUpgradeCasingMap.put(0, new ItemStack(this, 1, 0));
        GregTech_API.registerMachineBlock(this, -1);
    }

    public PhotonControllerUpgradeCasing(String unlocalizedName, String localName) {
        this();
        this.unlocalizedName = unlocalizedName;
        texter(localName, unlocalizedName + ".name");
    }

    // endregion
    // -----------------------
    // region Member Variables

    public static Map<Integer, ItemStack> PhotonControllerUpgradeCasingMap = new HashMap<>();

    /**
     * The Speed Increment of the Upgrade Casing.
     * Total Speed Increment is the sum of each Upgrade Casing.
     * Total value 10,000 means time cost *0.5 .
     */
    public static int[] speedIncrement = new int[] { /* LV */100, /* MV */200, /* HV */300, /* EV */400, /* IV */500,
        /* LuV */1000, /* ZPM */2000, /* UV */4000, /* UHV */7000, /* UEV */10000, /* UIV */14000, /* UMV */19000,
        /* UXV */25000, /* MAX */32000 };

    private IIcon blockIcon;
    private String unlocalizedName;

    // endregion
    // -----------------------
    // region Meta Generator

    public static ItemStack photonControllerUpgradeCasingMeta(String i18nName, int meta) {
        // Handle the name
        texter(i18nName, PhotonControllerUpgrade.getUnlocalizedName() + "." + meta + ".name");

        // Set the speedIncrement value
        // PhotonControllerUpgradeCasing.speedIncrement[meta] = speedIncrement;

        // Create the ItemStack
        ItemStack generatedItemStack = new ItemStack(PhotonControllerUpgrade, 1, meta);
        PhotonControllerUpgradeCasingMap.put(meta, generatedItemStack);
        return generatedItemStack;
    }

    // endregion
    // -----------------------
    // region Getters

    public int getSpeedIncrement(int meta) {
        return speedIncrement[meta];
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    // endregion
    // -----------------------
    // region Setters

    public void setSpeedIncrement(int speedIncrement, int meta) {
        PhotonControllerUpgradeCasing.speedIncrement[meta] = speedIncrement;
    }

    // endregion
    // -----------------------
    // region Overrides
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta < iconsBlockPhotonControllerUpgradeMap.size() ? iconsBlockPhotonControllerUpgradeMap.get(meta)
            : iconsBlockPhotonControllerUpgradeMap.get(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("gtnhcommunitymod:PhotonControllerUpgrades/0");
        for (int Meta : PhotonControllerUpgradeCasingMap.keySet()) {
            iconsBlockPhotonControllerUpgradeMap
                .put(Meta, reg.registerIcon("gtnhcommunitymod:PhotonControllerUpgrades/" + Meta));
        }
    }

    @Override
    public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData) {
        if (GregTech_API.isMachineBlock(this, aWorld.getBlockMetadata(aX, aY, aZ))) {
            GregTech_API.causeMachineUpdate(aWorld, aX, aY, aZ);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item aItem, CreativeTabs aCreativeTabs, List list) {
        for (int Meta : PhotonControllerUpgradeCasingMap.keySet()) {
            list.add(new ItemStack(aItem, 1, Meta));
        }
    }

    // endregion

}
