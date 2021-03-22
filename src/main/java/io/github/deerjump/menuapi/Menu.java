package io.github.deerjump.menuapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    public Menu() {
        this("Menu");
    }

    public Menu(String title) {
        this.inventory = Bukkit.createInventory(this, 9, title);
    }

    public Menu(InventoryType type) {
        this(type, "Menu");
    }

    public Menu(InventoryType type, String title) {
        this.inventory = Bukkit.createInventory(this, type, title);
    }

    public Menu(int size) {
        this(size, "Menu");
    }

    public Menu(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public abstract boolean onClick(Player player, int slot, ClickType type);

    public abstract void onOpen(Player player);

    public abstract void onClose(Player player);

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