package com.GTNH_Community.gtnhcommunitymod.common.item.items;

import static com.GTNH_Community.gtnhcommunitymod.client.GTCMCreativeTabs.tabMetaItem01;

import net.minecraft.item.Item;

import com.GTNH_Community.gtnhcommunitymod.common.item.itemAdders.ItemAdder01;
import com.GTNH_Community.gtnhcommunitymod.common.item.itemAdders.ItemProofOfHeroes;

public final class BasicItems {

    public static final Item MetaItem01 = new ItemAdder01("MetaItem01Base", "MetaItem01", tabMetaItem01)
        .setTextureName("gtnhcommunitymod:MetaItem01/0");

    public static final Item ProofOfHeroes = new ItemProofOfHeroes("英雄の証", "ProofOfHeroes", tabMetaItem01)
        .setTextureName("gtnhcommunitymod:ProofOfHeroes");

}
