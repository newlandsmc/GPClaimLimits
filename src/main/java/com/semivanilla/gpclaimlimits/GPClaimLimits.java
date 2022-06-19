package com.semivanilla.gpclaimlimits;

import com.semivanilla.gpclaimlimits.config.Config;
import com.semivanilla.gpclaimlimits.listener.GPListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GPClaimLimits extends JavaPlugin {

    private static GPClaimLimits instance;

    @Override
    public void onEnable() {
        instance = this;
        Config.init();
        getServer().getPluginManager().registerEvents(new GPListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static GPClaimLimits getInstance() {
        return instance;
    }
}
