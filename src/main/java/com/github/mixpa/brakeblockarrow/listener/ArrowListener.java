package com.github.mixpa.brakeblockarrow.listener;

import com.github.mixpa.brakeblockarrow.BrakeBlockArrowPlugin;
import com.github.mixpa.brakeblockarrow.arrow.ArrowManager;
import com.github.mixpa.brakeblockarrow.util.Message;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowListener implements Listener {
    @EventHandler
    public void whenPlayerShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getProjectile() instanceof Arrow) {
                if (event.getBow().hasItemMeta()) {
                    for (String lore : event.getBow().getItemMeta().getLore()) {
                        if (lore.equals("锻造：爆炸")) {
                            ArrowManager.arrowSet.add((Arrow) event.getProjectile());
                            return;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void whenArrowHit(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            if (BrakeBlockArrowPlugin.isDebug()) {
                String hitName;
                if (event.getHitEntity() != null) {
                    hitName = event.getHitEntity().getName();
                } else hitName = event.getHitBlock().getType().name();
                Message.debugInfo("射出的溅射物为" + event.getEntity().getType().name() + "射到的是" + hitName);
            }
            if (event.getEntity() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getEntity();
                if (ArrowManager.arrowSet.contains(arrow)) {
                    ArrowManager.arrowSet.remove(arrow);
                    arrow.getWorld().createExplosion(arrow.getLocation(), 1.5F);
                }
            }
        }
    }
}
