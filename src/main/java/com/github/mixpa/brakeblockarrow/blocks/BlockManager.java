package com.github.mixpa.brakeblockarrow.blocks;

import org.bukkit.block.Block;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockManager {
    public static Map<Block, Durability> blockDurabilityMap = new ConcurrentHashMap<>();
}
