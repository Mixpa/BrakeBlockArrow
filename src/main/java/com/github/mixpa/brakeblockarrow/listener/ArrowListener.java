package com.github.mixpa.brakeblockarrow.listener;

import com.github.mixpa.brakeblockarrow.BrakeBlockArrowPlugin;
import com.github.mixpa.brakeblockarrow.arrow.ArrowManager;
import com.github.mixpa.brakeblockarrow.blocks.BlockManager;
import com.github.mixpa.brakeblockarrow.blocks.Durability;
import com.github.mixpa.brakeblockarrow.util.Message;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
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
                    Entity arrow = event.getProjectile();
                    for (String lore : event.getBow().getItemMeta().getLore()) {
                        switch (lore) {
                            case "锻造：爆炸": {
                                ArrowManager.arrowExplosionSet.add((Arrow) arrow);
                                break;
                            }
                            case "锻造：击破": {
                                ArrowManager.arrowBrakeSet.add(((Arrow) arrow));
                                break;
                            }
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
                if (ArrowManager.arrowExplosionSet.contains(arrow)) {
                    ArrowManager.arrowExplosionSet.remove(arrow);
                    arrow.getWorld().createExplosion(arrow.getLocation(), 1.5F);
                }
                if (event.getHitBlock() == null) return;
                if (ArrowManager.arrowBrakeSet.contains(arrow)) {
                    ArrowManager.arrowBrakeSet.remove(arrow);
                    //todo 查看耐久度如果为0破坏
                    Durability durability;
                    if (BlockManager.blockDurabilityMap.containsKey(event.getHitBlock())) {
                        durability = BlockManager.blockDurabilityMap.get(event.getHitBlock());
                    } else {
                        durability = new Durability(event.getHitBlock());
                    }
                    durability.attrit(1);
                    if (durability.isExhausted()) {
                        //todo 掉落率计算
                        event.getHitBlock().breakNaturally();
                    } else {
                        BlockManager.blockDurabilityMap.put(event.getHitBlock(), durability);
                        ((Player) arrow.getShooter()).sendMessage(Message.message("击中了" + event.getHitBlock().getType().name() + " 剩余的耐久度为" + durability.getDurability()));
                    }
                }
            }
        }
    }
}
