package com.github.mixpa.brakeblockarrow.listener;

import com.github.mixpa.brakeblockarrow.arrow.ArrowManager;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {
    @EventHandler
    public void whenEntityExplode(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Arrow)) {
            return;
        }
        if (!ArrowManager.arrowExplosionSet.contains(((Arrow) event.getEntity()))) {
            return;
        }
        //todo
    }
}
