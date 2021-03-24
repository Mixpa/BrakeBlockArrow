package com.github.mixpa.brakeblockarrow.blocks;

import com.google.common.collect.Maps;
import org.bukkit.block.Block;

import java.util.concurrent.ConcurrentHashMap;

public class BlockManager {
    public static ConcurrentHashMap<Block, Durability> blockDurabilityMap = new ConcurrentHashMap<>();
}
