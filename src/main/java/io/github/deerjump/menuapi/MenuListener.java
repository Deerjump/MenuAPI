package io.github.deerjump.menuapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

/**
 * This class will be used only once to initialize the EventHandlers for <b>EVERY</b>
 * instance of Menu that you have. <br>
 * You <b>DO NOT NEED</b> a separate Listener for every Menu.
 */
@SuppressWarnings("unused")
public class MenuListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onClick(InventoryClickEvent event) {
        final InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof Menu)) return;

        ((Menu) holder).onClick(event);
    }

    @EventHandler private void onOpen(InventoryOpenEvent event) {
        final InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof Menu)) return;

        ((Menu) holder).onOpen(event);
    }

    @EventHandler private void onClose(InventoryCloseEvent event) {
        final InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof Menu)) return;

        ((Menu) holder).onClose(event);
    }
}
