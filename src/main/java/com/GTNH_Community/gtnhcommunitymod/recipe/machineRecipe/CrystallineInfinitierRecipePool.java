package com.GTNH_Community.gtnhcommunitymod.recipe.machineRecipe;

import static com.GTNH_Community.gtnhcommunitymod.common.GTCMItemList.EnergyCrystalShard;
import static com.GTNH_Community.gtnhcommunitymod.common.GTCMItemList.LapotronShard;
import static com.GTNH_Community.gtnhcommunitymod.common.GTCMItemList.PerfectEnergyCrystal;
import static com.GTNH_Community.gtnhcommunitymod.common.GTCMItemList.PerfectLapotronCrystal;
import static gregtech.api.enums.TierEU.RECIPE_UHV;
import static gregtech.api.enums.TierEU.RECIPE_UV;
import static gregtech.api.enums.TierEU.RECIPE_ZPM;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.GTNH_Community.gtnhcommunitymod.common.machine.recipeMap.GTCMRecipe;
import com.GTNH_Community.gtnhcommunitymod.common.material.MaterialPool;
import com.GTNH_Community.gtnhcommunitymod.recipe.IRecipePool;
import com.dreammaster.gthandler.CustomItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;

public class CrystallineInfinitierRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {

        final GT_Recipe.GT_Recipe_Map AC = GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes;
        final GT_Recipe.GT_Recipe_Map LE = GT_Recipe.GT_Recipe_Map.sLaserEngraverRecipes;
        final GT_Recipe.GT_Recipe_Map HM = GT_Recipe.GT_Recipe_Map.sHammerRecipes;
        final GT_Recipe.GT_Recipe_Map CI = GTCMRecipe.instance.CrystallineInfinitierRecipes;

        // region Lapotron circuit
        // Shard
        GT_Values.RA.stdBuilder()
            .itemInputs(PerfectLapotronCrystal.get(1))
            .noFluidInputs()
            .itemOutputs(LapotronShard.get(64))
            .noFluidOutputs()
            .eut(RECIPE_UV)
            .duration(20 * 5)
            .addTo(HM);

        // Growth
        GT_Values.RA.stdBuilder()
            .itemInputs(LapotronShard.get(1), MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 8))
            .fluidInputs(Materials.VibrantAlloy.getMolten(144 * 2))
            .itemOutputs(PerfectLapotronCrystal.get(1))
            .noFluidOutputs()
            .eut(RECIPE_UHV)
            .duration(20 * 30)
            .addTo(CI);

        // The first piece
        GT_Values.RA.stdBuilder()
            .itemInputs(
                CustomItemList.RawLapotronCrystal.get(16),
                CustomItemList.LapotronDust.get(64),
                CustomItemList.LapotronDust.get(64),
                CustomItemList.LapotronDust.get(64))
            .fluidInputs(MaterialPool.HolmiumGarnet.getMolten(144 * 256))
            .itemOutputs(LapotronShard.get(1))
            .noFluidOutputs()
            .outputChances(999)
            .eut(RECIPE_UHV)
            .duration(20 * 3000)
            .addTo(CI);

        // Holmium Garnet Dust
        GT_Values.RA.stdBuilder()
            .itemInputs(GT_Utility.getIntegratedCircuit(4), Materials.Holmium.getDust(3), Materials.Sapphire.getDust(5))
            .noFluidInputs()
            .itemOutputs(MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 8))
            .noFluidOutputs()
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(20 * 5)
            .addTo(GT_Recipe.GT_Recipe_Map.sMixerRecipes);

        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(4),
                Materials.Holmium.getDust(3),
                Materials.GreenSapphire.getDust(10))
            .noFluidInputs()
            .itemOutputs(MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 8))
            .noFluidOutputs()
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(20 * 5)
            .addTo(GT_Recipe.GT_Recipe_Map.sMixerRecipes);

        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(5),
                Materials.Holmium.getDust(60),
                Materials.Sapphire.getDust(64),
                Materials.Sapphire.getDust(36))
            .noFluidInputs()
            .itemOutputs(
                MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 64),
                MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 64),
                MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 32))
            .noFluidOutputs()
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(20 * 30)
            .addTo(GT_Recipe.GT_Recipe_Map.sMixerRecipes);

        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_Utility.getIntegratedCircuit(5),
                Materials.Holmium.getDust(60),
                Materials.GreenSapphire.getDust(64),
                Materials.GreenSapphire.getDust(64),
                Materials.GreenSapphire.getDust(64),
                Materials.GreenSapphire.getDust(8))
            .noFluidInputs()
            .itemOutputs(
                MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 64),
                MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 64),
                MaterialPool.HolmiumGarnet.get(OrePrefixes.dust, 32))
            .noFluidOutputs()
            .noOptimize()
            .eut(RECIPE_UV)
            .duration(20 * 60)
            .addTo(GT_Recipe.GT_Recipe_Map.sMixerRecipes);

        // Chip
        for (ItemStack itemStack : OreDictionary.getOres("craftingLensPurple")) {
            GT_Values.RA.stdBuilder()
                .itemInputs(GT_Utility.copyAmount(0, itemStack), PerfectLapotronCrystal.get(1))
                .noFluidInputs()
                .itemOutputs(
                    ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                    ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                    ItemList.Circuit_Parts_Crystal_Chip_Master.get(64),
                    ItemList.Circuit_Parts_Crystal_Chip_Master.get(64))
                .noFluidOutputs()
                .noOptimize()
                .eut(RECIPE_UHV)
                .duration(20 * 15)
                .addTo(LE);
        }

        // endregion

        // region Energy crystal
        // Shard
        GT_Values.RA.stdBuilder()
            .itemInputs(PerfectEnergyCrystal.get(1))
            .noFluidInputs()
            .itemOutputs(EnergyCrystalShard.get(64))
            .noFluidOutputs()
            .eut(RECIPE_ZPM)
            .duration(20 * 5)
            .addTo(HM);

        // Growth
        GT_Values.RA.stdBuilder()
            .itemInputs(EnergyCrystalShard.get(1), ItemList.IC2_Energium_Dust.get(64))
            .fluidInputs(Materials.EnergeticAlloy.getMolten(144 * 2))
            .itemOutputs(PerfectEnergyCrystal.get(1))
            .noFluidOutputs()
            .eut(RECIPE_ZPM)
            .duration(20 * 30)
            .addTo(CI);

        // The first piece
        GT_Values.RA.stdBuilder()
            .itemInputs(ItemList.IC2_Energium_Dust.get(64), ItemList.IC2_Energium_Dust.get(64))
            .fluidInputs(Materials.Redstone.getMolten(144 * 1024))
            .itemOutputs(EnergyCrystalShard.get(1))
            .noFluidOutputs()
            .outputChances(1111)
            .eut(RECIPE_ZPM)
            .duration(20 * 3000)
            .addTo(AC);

        // Chip
        for (ItemStack itemStack : OreDictionary.getOres("craftingLensRed")) {
            GT_Values.RA.stdBuilder()
                .itemInputs(GT_Utility.copyAmount(0, itemStack), PerfectEnergyCrystal.get(1))
                .noFluidInputs()
                .itemOutputs(CustomItemList.EngravedEnergyChip.get(64), CustomItemList.EngravedEnergyChip.get(64))
                .noFluidOutputs()
                .noOptimize()
                .eut(RECIPE_UV)
                .duration(20 * 15)
                .addTo(LE);
        }

        // endregion
    }
}
