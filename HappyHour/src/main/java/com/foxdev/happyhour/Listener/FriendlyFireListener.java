package com.foxdev.happyhour.Listener;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FriendlyFireListener implements Listener {

    private BossBar bossBar;
    private int timeLeft;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("happy.admin")) {
            player.sendMessage("/startff - Start the Happy Hour");
        }
    }

    public void startFriendlyFire() {
        Bukkit.broadcastMessage("Friendly Fire hour has started");

        bossBar = Bukkit.createBossBar("Friendly Fire Hour", BarColor.RED, BarStyle.SEGMENTED_10);
        bossBar.setProgress(1);
        bossBar.setVisible(true);

        timeLeft = 3600;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                updateBossBar();
            }
        }, 0L,20L);
        enableFriendlyFire();
    }


    private void updateBossBar(){
        timeLeft -= 20;
        double progress = timeLeft / 3600.0;
        bossBar.setProgress(progress);

        if(timeLeft <= 0){
            disableFriendlyFire();

            bossBar.setVisible(false);
            bossBar.removeAll();
            Bukkit.broadcastMessage("Friendly fire hour is ended");
        }
    }



    private void disableFriendlyFire(){
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setCanPickupItems(true);
            player.setInvulnerable(false);

        }

    }


    private void enableFriendlyFire(){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setCanPickupItems(true);
            player.setInvulnerable(false);
        }
    }

}
