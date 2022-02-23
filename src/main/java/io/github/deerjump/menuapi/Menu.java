package io.github.deerjump.menuapi;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Extend this class to create your custom Menu
 */
public abstract class Menu implements InventoryHolder {
    protected static String defaultTitle = "Menu";
    protected Actions<InventoryClickEvent> actions = new Actions<>();
    protected Inventory inventory;

    public Menu() {
        this(defaultTitle);
    }

    public Menu(String title) {
        this.inventory = Bukkit.createInventory(this, 9, title);
    }

    public Menu(InventoryType type) {
        this(type, type.getDefaultTitle());
    }

    public Menu(InventoryType type, String title) {
        this.inventory = Bukkit.createInventory(this, type, title);
    }

    public Menu(int size) {
        this(size, defaultTitle);
    }

    public Menu(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    protected void executeClickAction(UUID id, InventoryClickEvent event) {
        this.actions.getAction(id).accept(event);
    }

    public abstract void onClick(InventoryClickEvent event);

    public abstract void onOpen(InventoryOpenEvent event);

    public abstract void onClose(InventoryCloseEvent event);

    /**
     *  Helper function designed to fill in empty spaces of the menu. <br>
     *  Can be overriden to define custom behavior
     *  <p> </p>
     * @param item What wil fill the empty spaces
     */
    protected void fill(ItemStack item) {
        for(int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null ) continue;
            inventory.setItem(i, item);
        }
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Inventory getInventory() {
        return this.inventory;
    }
}