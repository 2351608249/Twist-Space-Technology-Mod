package com.Nxer.TwistSpaceTechnology.recipe.specialRecipe;

import net.minecraft.item.ItemStack;

import com.Nxer.TwistSpaceTechnology.common.GTCMItemList;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class TCResearches {

    public void loadResearches() {
        ResearchItem ElvenWorkshopResearch = new ResearchItem(
            "BH_ELVEN_WORKSHOP",
            "botanichorizons",
            new AspectList(),
            4,
            -9,
            3,
            GTCMItemList.ElvenWorkshop.get(1, 0));

        ElvenWorkshopResearch.setParents("BH_GAIA_PYLON");

        ElvenWorkshopResearch.setSecondary();

        ElvenWorkshopResearch.setPages(
            new ResearchPage("tc.research_text.BH_ELVEN_WORKSHOP.1"),
            new ResearchPage("tc.research_text.BH_ELVEN_WORKSHOP.2"),
            new ResearchPage(
                new InfusionRecipe(
                    "BH_ELVEN_WORKSHOP",
                    GTCMItemList.ElvenWorkshop.get(1, 0),
                    10,
                    (new AspectList()).merge(Aspect.LIFE, 64)
                        .merge(Aspect.EARTH, 64)
                        .merge(Aspect.MAGIC, 64)
                        .merge(Aspect.MECHANISM, 64),
                    new ItemStack(ModBlocks.terraPlate),
                    new ItemStack[] { ItemList.Field_Generator_EV.get(1), ItemList.Casing_IV.get(1),
                        Materials.Steeleaf.getPlates(1), new ItemStack(ModItems.spawnerMover, 1),
                        ItemList.Field_Generator_EV.get(1), ItemList.Casing_IV.get(1), Materials.Steeleaf.getPlates(1),
                        new ItemStack(ModItems.spawnerMover, 1) })));

        ElvenWorkshopResearch.registerResearchItem();
//
//        new ResearchItem(
//            "BH_ELVEN_WORKSHOP",
//            "botanichorizons",
//            new AspectList(),
//            4,
//            -9,
//            3,
//            GTCMItemList.ElvenWorkshop.get(1, 0))
//            .setParents("BH_GAIA_PYLON")
//            .setSecondary()
//            .setPages(
//                new ResearchPage("tc.research_text.BH_ELVEN_WORKSHOP.1"),
//                new ResearchPage("tc.research_text.BH_ELVEN_WORKSHOP.2"),
//                new ResearchPage(
//                    new InfusionRecipe(
//                        "BH_ELVEN_WORKSHOP",
//                        GTCMItemList.ElvenWorkshop.get(1, 0),
//                        10,
//                        (new AspectList()).merge(Aspect.LIFE, 64)
//                                          .merge(Aspect.EARTH, 64)
//                                          .merge(Aspect.MAGIC, 64)
//                                          .merge(Aspect.MECHANISM, 64),
//                        new ItemStack(ModBlocks.terraPlate),
//                        new ItemStack[] { ItemList.Field_Generator_EV.get(1), ItemList.Casing_IV.get(1),
//                            Materials.Steeleaf.getPlates(1), new ItemStack(ModItems.spawnerMover, 1),
//                            ItemList.Field_Generator_EV.get(1), ItemList.Casing_IV.get(1), Materials.Steeleaf.getPlates(1),
//                            new ItemStack(ModItems.spawnerMover, 1) })))
//            .registerResearchItem();

    }
}
