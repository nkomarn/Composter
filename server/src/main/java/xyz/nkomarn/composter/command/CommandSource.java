package xyz.nkomarn.composter.command;

import org.jetbrains.annotations.NotNull;

public interface CommandSource {

    void sendMessage(@NotNull String message);
}
