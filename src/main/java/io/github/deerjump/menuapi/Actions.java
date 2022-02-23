package io.github.deerjump.menuapi;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class Actions<T> {
    private final Map<UUID, Consumer<T>> actions = new HashMap<>();

    public Actions() {}

    public UUID registerAction(Consumer<T> action) {
        for (Map.Entry<UUID, Consumer<T>> entry : this.actions.entrySet()) {
            if (!entry.getValue().equals(action)) continue;

            return entry.getKey();
        }

        UUID uuid = UUID.randomUUID();
        this.actions.put(uuid, action);
        return uuid;
    }

    public Consumer<T> getAction(UUID id) {
        return this.actions.getOrDefault(id, (arg) -> {});
    }

    public void execute(UUID id, T args) {
        this.getAction(id).accept(args);
    }
}
