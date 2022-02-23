package io.github.deerjump.menuapi;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import java.util.UUID;
import java.util.function.Consumer;

import static org.bukkit.persistence.PersistentDataType.STRING;

public abstract class Menu implements InventoryHolder {
    protected static String defaultTitle = "Menu";
    protected static final NamespacedKey PDC_ACTION_KEY = new NamespacedKey("menu", "action");
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

    public abstract void onClick(InventoryClickEvent event);

    public abstract void onOpen(InventoryOpenEvent event);

    public abstract void onClose(InventoryCloseEvent event);

    protected void executeClickAction(ItemStack item, InventoryClickEvent event) {
        if (item == null || item.getItemMeta() == null) return;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        String uuidString = pdc.get(PDC_ACTION_KEY, STRING);
        if (uuidString == null) return;

        UUID uuid = UUID.fromString(uuidString);
        this.actions.execute(uuid, event);
    }

    private ItemStack applyActionId(ItemStack item, UUID uuid) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(PDC_ACTION_KEY, STRING, uuid.toString());
        item.setItemMeta(meta);
        return item;
    }

    protected void addItem(Material material, Consumer<InventoryClickEvent> action) {
        this.addItem(new ItemStack(material), action);
    }

    protected void addItem(ItemStack item, Consumer<InventoryClickEvent> action) {
        UUID uuid = this.actions.registerAction(action);
        ItemStack taggedItem = this.applyActionId(item, uuid);

        this.inventory.addItem(taggedItem);
    }

    protected void setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> action) {
        UUID uuid = this.actions.registerAction(action);
        ItemStack taggedItem = this.applyActionId(item, uuid);
        this.inventory.setItem(slot, taggedItem);
    }

    protected void setItem(int slot, Material material, Consumer<InventoryClickEvent> action) {
        this.setItem(slot, new ItemStack(material), action);
    }

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

    protected void fill(Material material, String label) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) throw new IllegalArgumentException("Material must be able to generate an ItemMeta!");

        itemMeta.setDisplayName(label);

        this.fill(itemStack);
    }

    protected void fill(Material material) {
        this.fill(material, " ");
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Inventory getInventory() {
        return this.inventory;
    }
}