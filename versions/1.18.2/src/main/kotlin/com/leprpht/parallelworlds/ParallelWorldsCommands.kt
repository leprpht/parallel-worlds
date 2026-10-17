package com.leprpht.parallelworlds

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandSourceStack

object ParallelWorldsCommands {

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        // Command v2 was not present on the 1.18.2 Fabric API line. The
        // portal implementation does not depend on these optional commands.
    }
}
