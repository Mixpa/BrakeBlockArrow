package com.github.mixpa.brakeblockarrow.blocks;

import lombok.Getter;
import org.bukkit.block.Block;

public class Durability {
    @Getter
    private int durability;

    Durability() {
        durability = 1;
    }

    Durability(Block block) {
        switch (block.getType()) {
            default:
                durability = 1;
        }
    }

    Durability(int durability) {
        if (durability < 1) throw new IllegalArgumentException("磨损的数值不能为负数或0!");
        this.durability = durability;
    }

    public void attrit(int counts) {

    }
}
