package io.github.deerjump.menuapi.examples;

import io.github.deerjump.menuapi.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public class ExampleMenu extends Menu {

    public ExampleMenu() {
        super(18, "Example");
        inventory.setItem(0, new ItemStack(Material.STONE));
        inventory.setItem(1, new ItemStack(Material.COBBLESTONE));
        inventory.setItem(2, new ItemStack(Material.DIRT));

        ItemStack filler = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);

        this.fill(filler);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        Material type = clickedItem == null ? Material.AIR : clickedItem.getType();
        int rawSlot = event.getRawSlot();

        switch (rawSlot) {
            case 0: { player.sendMessage(type + " clicked on the stone"); break; }
            case 1: { player.sendMessage(type + " clicked on the cobblestone"); break; }
            case 2: { player.sendMessage(type + " clicked on the dirt"); break; }
            default: { player.sendMessage("You clicked on: " + inventory.getItem(rawSlot)); break; }
        }

    }

    @Override
    public void onOpen(InventoryOpenEvent event) {}

    @Override
    public void onClose(InventoryCloseEvent player) {}
}
