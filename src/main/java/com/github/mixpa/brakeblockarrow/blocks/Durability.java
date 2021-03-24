package com.github.mixpa.brakeblockarrow.blocks;

import lombok.Getter;
import org.bukkit.block.Block;

public class Durability {
    @Getter
    private int durability;

    Durability() {
        durability = 1;
    }

    public Durability(Block block) {
        switch (block.getType()) {
            default:
                durability = 1;
        }
    }

    public Durability(int durability) {
        if (durability < 1) throw new IllegalArgumentException("耐久度的初始数值不能为负数或0!");
        this.durability = durability;
    }

    /**
     * @param counts 磨损的耐久度数量
     * @return 如果耐久度已经耗尽会返回true，否则返回false
     */
    public boolean attrit(int counts) {
        durability = durability - counts;
        if (durability < 1) {
            durability = 0;
            return true;
        } else return false;
    }

    public boolean isExhausted() {
        return durability == 0;
    }
}
