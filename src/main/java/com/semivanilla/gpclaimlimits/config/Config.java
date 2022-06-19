package com.semivanilla.gpclaimlimits.config;

import com.google.common.base.Throwables;
import com.semivanilla.gpclaimlimits.GPClaimLimits;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.logging.Level;

public class Config {

    private static final String HEADER = "";

    private static File CONFIG_FILE;
    public static File CONFIG_PATH;
    public static YamlConfiguration config;

    static int version;

    public static void init() {
        CONFIG_PATH = GPClaimLimits.getInstance().getDataFolder();
        CONFIG_FILE = new File(CONFIG_PATH, "config.yml");
        config = new YamlConfiguration();
        try {
            config.load(CONFIG_FILE);
        } catch (IOException ignore) {
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not load config.yml, please correct your syntax errors", ex);
            Throwables.throwIfUnchecked(ex);
        }
        config.options().header(HEADER);
        config.options().copyDefaults(true);

        version = getInt("config-version", 1);
        set("config-version", 2);

        readConfig(Config.class, null);
    }

    static void readConfig(Class<?> clazz, Object instance) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPrivate(method.getModifiers())) {
                if (method.getParameterTypes().length == 0 && method.getReturnType() == Void.TYPE) {
                    try {
                        method.setAccessible(true);
                        method.invoke(instance);
                    } catch (InvocationTargetException ex) {
                        Throwables.throwIfUnchecked(ex);
                    } catch (Exception ex) {
                        Bukkit.getLogger().log(Level.SEVERE, "Error invoking " + method, ex);
                    }
                }
            }
        }
        saveConfig();
    }

    static void saveConfig() {
        try {
            config.save(CONFIG_FILE);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save " + CONFIG_FILE, ex);
        }
    }

    private static void set(String path, Object val) {
        config.addDefault(path, val);
        config.set(path, val);
    }

    private static boolean getBoolean(String path, boolean def) {
        config.addDefault(path, def);
        return config.getBoolean(path, config.getBoolean(path));
    }

    private static double getDouble(String path, double def) {
        config.addDefault(path, def);
        return config.getDouble(path, config.getDouble(path));
    }

    private static int getInt(String path, int def) {
        config.addDefault(path, def);
        return config.getInt(path, config.getInt(path));
    }

    private static <T> List getList(String path, T def) {
        config.addDefault(path, def);
        return config.getList(path, config.getList(path));
    }

    private static String getString(String path, String def) {
        config.addDefault(path, def);
        return config.getString(path, config.getString(path));
    }

    protected static void log(Level level, String s) {
        Bukkit.getLogger().log(level, s);
    }

    /** ONLY EDIT BELOW THIS LINE **/
    public static String claimLimitReached = "<red>You have reached your current claim limit.";
    public static String claimAboveLimit = "<red>You can not create this claim as you are already at your limit.";
    private static void messageSettings() {
        claimLimitReached = getString("messages.claim-limit-reached", claimLimitReached);
        claimAboveLimit = getString("messages.claim-above-limit", claimAboveLimit);
    }

}
