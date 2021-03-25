package com.github.mixpa.brakeblockarrow.blocks;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;
import java.util.Set;

public class DurabilityConfig {
    @Getter
    private static Map<Material, Integer> configMap;

    DurabilityConfig() {
    }

    public static DurabilityConfig deserialize(ConfigurationSection section) {
        configMap = Maps.newHashMap();
        Set<String> keys = section.getKeys(false);
        keys.forEach(key -> {
            Material material = Material.matchMaterial(key);
            if (material != null) {
                configMap.put(material, section.getInt(key));
            }
        });
        return null;
    }
}
