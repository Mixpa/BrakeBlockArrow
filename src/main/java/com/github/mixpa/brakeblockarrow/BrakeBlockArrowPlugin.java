package com.github.mixpa.brakeblockarrow;

import com.github.mixpa.brakeblockarrow.commands.BrakeBlockArrowCommand;
import com.github.mixpa.brakeblockarrow.listener.ArrowListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class BrakeBlockArrowPlugin extends JavaPlugin {
    @Getter
    private static boolean debug = true;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ArrowListener(), this);
        getCommand("BrakeBlockArrow").setExecutor(new BrakeBlockArrowCommand());
    }
}
