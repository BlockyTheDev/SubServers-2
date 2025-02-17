package net.ME1312.SubServers.Sync.Event;

import net.ME1312.Galaxi.Library.Util;
import net.ME1312.Galaxi.Library.Version.Version;
import net.ME1312.SubServers.Bungee.Library.SubEvent;
import net.ME1312.SubServers.Client.Common.Network.API.SubServer;
import net.ME1312.SubServers.Sync.SubAPI;

import net.md_5.bungee.api.plugin.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Server Created Event
 */
public class SubCreatedEvent extends Event implements SubEvent {
    private UUID player;
    private boolean success;
    private boolean update;
    private String host;
    private String name;
    private String template;
    private Version version;
    private int port;

    /**
     * Server Created Event
     *
     * @param player Player Creating
     * @param host Potential Host
     * @param name Server Name
     * @param template Server Type
     * @param version Server Version
     * @param port Server Port Number
     */
    public SubCreatedEvent(UUID player, String host, String name, String template, Version version, int port, boolean update, boolean success) {
        Util.nullpo(host, name, template, port);
        this.player = player;
        this.success = success;
        this.update = update;
        this.host = host;
        this.name = name;
        this.template = template;
        this.version = version;
        this.port = port;
    }

    /**
     * Get the Host the SubServer runs on
     *
     * @return Host
     */
    public String getHost() {
        return host;
    }

    /**
     * Get if SubCreator was being run in update mode
     *
     * @return Update Mode Status
     */
    public boolean wasUpdate() {
        return update;
    }

    /**
     * Get if the operation was a success
     *
     * @return Success Status
     */
    public boolean wasSuccessful() {
        return success;
    }

    /**
     * Get the Server that's being updated
     *
     * @param callback Updating Server
     */
    public void getServer(Consumer<SubServer> callback) {
        if (!update && !success) {
            try {
                callback.accept(null);
            } catch (Throwable e) {
                Throwable ew = new InvocationTargetException(e);
                ew.printStackTrace();
            }
        } else {
            SubAPI.getInstance().getSubServer(name, callback);
        }
    }

    /**
     * Get the name the SubServer used
     *
     * @return SubServer Name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Template that was used
     *
     * @return Server Template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Get the Version the Server used
     *
     * @return Server Version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Get the Port the Server used
     *
     * @return Port Number
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the player that triggered the Event
     *
     * @return The Player that triggered this Event or null if Console
     */
    public UUID getPlayer() { return player; }
}
