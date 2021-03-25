package com.github.mixpa.brakeblockarrow.blocks;

import com.google.common.collect.Maps;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockManager {
    public static Map<Block, Durability> blockDurabilityMap = new ConcurrentHashMap<>();
    public static Map<World, net.minecraft.server.v1_12_R1.World> worldMap = Maps.newHashMap();
}
