package io.github.deerjump.menuapi.examples;

import io.github.deerjump.menuapi.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public class ExampleMenu extends Menu {

    public ExampleMenu() {
        super(18,"Example");
    }

    @Override
    public boolean onClick(Player player, int slot, ClickType type) {
        return true;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onOpen(Player player) {
        inventory.setItem(0, new ItemStack(Material.STONE));
        inventory.setItem(1, new ItemStack(Material.STONE));
        inventory.setItem(2, new ItemStack(Material.STONE));

        ItemStack filler = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);

        this.fill(filler);
    }

    @Override
    public void onClose(Player player) {

    }
}
