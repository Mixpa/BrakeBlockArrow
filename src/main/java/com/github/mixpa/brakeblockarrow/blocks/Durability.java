package com.github.mixpa.brakeblockarrow.blocks;

import lombok.Getter;
import org.bukkit.block.Block;

public class Durability {
    @Getter
    private int durability;
    Durability(){
        durability = 1;
    }
    Durability(Block block){
        switch (block.getType()){
            default:durability = 1;
        }
    }
    Durability(int durability){
        this.durability = durability;
    }
}
