package com.github.mixpa.brakeblockarrow.events;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;

public class DurabilityExhaustedEvent extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();

    public DurabilityExhaustedEvent(Block block) {
        super(block);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
