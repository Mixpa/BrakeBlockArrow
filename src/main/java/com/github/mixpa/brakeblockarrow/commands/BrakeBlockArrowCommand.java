package com.github.mixpa.brakeblockarrow.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;


public class BrakeBlockArrowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (strings.length > 0) {
            switch (strings[0].toLowerCase()) {
                case "give": {
                    ItemStack bow = new ItemStack(Material.BOW);
                    ItemMeta bowMeta = Bukkit.getServer().getItemFactory().getItemMeta(Material.BOW);
                    bowMeta.setLore(Collections.singletonList("锻造：爆炸"));
                    bow.setItemMeta(bowMeta);
                    ((Player) commandSender).getInventory().addItem(bow);
                }
            }
        }
        return true;
    }
}
