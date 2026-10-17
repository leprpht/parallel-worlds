package com.leprpht.parallelworlds

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandSourceStack

object ParallelWorldsCommands {

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        // The diagnostic command is omitted on this pre-command-v2 adapter.
    }
}
