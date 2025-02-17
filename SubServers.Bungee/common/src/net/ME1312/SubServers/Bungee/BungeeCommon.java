package net.ME1312.SubServers.Bungee;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

/**
 * BungeeCord Common Layout Class
 */
public abstract class BungeeCommon extends BungeeCord {
    private static BungeeCommon instance;
    final Supplier<BungeeAPI> api;

    protected BungeeCommon(Supplier<BungeeAPI> api) throws IOException {
        this.api = api;
        instance = this;
    }

    /**
     * Get the name from BungeeCord's original signature (for determining which fork is being used)
     *
     * @return BungeeCord Software Name
     */
    public abstract String getBungeeName();

    /**
     * Waterfall's getServersCopy()
     *
     * @return Server Map Copy
     */
    public abstract Map<String, ServerInfo> getServersCopy();

    /**
     * Gets the ProxyServer Common Object
     *
     * @return ProxyServer Common
     */
    public static BungeeCommon getInstance() {
        return instance;
    }
}
