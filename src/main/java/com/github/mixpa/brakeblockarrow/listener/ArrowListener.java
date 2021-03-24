package com.github.mixpa.brakeblockarrow.listener;

import com.github.mixpa.brakeblockarrow.arrow.ArrowManager;
import com.github.mixpa.brakeblockarrow.blocks.BlockManager;
import com.github.mixpa.brakeblockarrow.blocks.Durability;
import com.github.mixpa.brakeblockarrow.util.Message;
import org.bukkit.block.Block;
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
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (!(event.getProjectile() instanceof Arrow)) {
            return;
        }
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

    @EventHandler
    public void whenArrowHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        if (!(event.getEntity() instanceof Arrow)) {
            return;
        }
        if (event.getHitBlock() == null) {
            return;
        }
        Arrow arrow = (Arrow) event.getEntity();
        Block block = event.getHitBlock();
        if (ArrowManager.arrowExplosionSet.contains(arrow)) {
            ArrowManager.arrowExplosionSet.remove(arrow);
            arrow.getWorld().createExplosion(arrow.getLocation(), 1.5F);
        }
        if (ArrowManager.arrowBrakeSet.contains(arrow)) {
            ArrowManager.arrowBrakeSet.remove(arrow);
            //todo 查看耐久度如果为0破坏
            Durability durability;
            if (BlockManager.blockDurabilityMap.containsKey(block)) {
                durability = BlockManager.blockDurabilityMap.get(block);
            } else {
                durability = new Durability(block);
            }
            durability.attrit(1);
            if (durability.isExhausted()) {
                //todo 掉落率计算
                block.breakNaturally();
            } else {
                BlockManager.blockDurabilityMap.put(block, durability);
                ((Player) arrow.getShooter()).sendMessage(Message.message("击中了" + block.getType().name() + " 剩余的耐久度为" + durability.getDurability()));
            }
        }
    }
}
