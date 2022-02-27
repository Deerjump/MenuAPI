package io.github.deerjump.menuapi.examples;

import io.github.deerjump.menuapi.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


@SuppressWarnings("unused")
public class ExampleMenu extends Menu {
    private static final String TITLE = "Example";
    private static final int SIZE = 18;

    public ExampleMenu() {
        super(SIZE, TITLE);

        this.setItem(0, new ItemStack(Material.STONE), this::displayItemClicked);
        this.setItem(1, Material.COBBLESTONE, this::displayItemClicked);
        this.addItem(new ItemStack(Material.DIRT), this::displayItemClicked);
        this.addItem(Material.GRAVEL, this::displayItemClicked);

        this.fill(Material.WHITE_STAINED_GLASS_PANE);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        this.executeClickAction(event.getCurrentItem(), event);
    }


    private void displayItemClicked(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        Material type = clickedItem == null ? Material.AIR : clickedItem.getType();
        player.sendMessage("You clicked on: " + type);
    }

    private void giveItemClicked(InventoryClickEvent event) {

    }
}
