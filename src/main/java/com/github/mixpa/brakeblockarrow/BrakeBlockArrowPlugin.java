package com.github.mixpa.brakeblockarrow;

import com.github.mixpa.brakeblockarrow.blocks.DurabilityConfig;
import com.github.mixpa.brakeblockarrow.commands.BrakeBlockArrowCommand;
import com.github.mixpa.brakeblockarrow.listener.ArrowListener;
import com.github.mixpa.brakeblockarrow.listener.ExplosionListener;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BrakeBlockArrowPlugin extends JavaPlugin {
    @Getter
    private static boolean debug = true;

    @Override
    public void onEnable() {
        DurabilityConfig.deserialize(loadConfig("durability.yml"));
        getServer().getPluginManager().registerEvents(new ArrowListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getCommand("BrakeBlockArrow").setExecutor(new BrakeBlockArrowCommand());
    }

    private FileConfiguration loadConfig(String name) {
        File file = new File(getDataFolder(), name);
        if (!file.exists()) {
            saveResource(name, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }
}
