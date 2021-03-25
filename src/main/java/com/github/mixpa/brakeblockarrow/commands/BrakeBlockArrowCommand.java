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
    private static void addBow(Player player, String lore) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = Bukkit.getServer().getItemFactory().getItemMeta(Material.BOW);
        bowMeta.setLore(Collections.singletonList(lore));
        bow.setItemMeta(bowMeta);
        player.getInventory().addItem(bow);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        if (strings.length > 0) {
            Player player = (Player) commandSender;
            switch (strings[0].toLowerCase()) {
                case "给予":
                case "give": {
                    if (strings.length != 2) {
                        return true;
                    }
                    switch (strings[1].toLowerCase()) {
                        case "爆炸":
                        case "boom": {
                            addBow(player, "锻造：爆炸");
                            break;
                        }
                        case "brake":
                        case "击破": {
                            addBow(player, "锻造：击破");
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }
}
