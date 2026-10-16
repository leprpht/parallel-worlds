package com.leprpht.parallelworlds

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component

object ParallelWorldsCommands {

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("parallelworlds")
                .executes { context ->
                    context.source.sendSuccess(
                        { Component.literal("Parallel Worlds is running.") },
                        false,
                    )
                    1
                }
                .then(
                    Commands.literal("info").executes { context ->
                        val player = context.source.playerOrException
                        val level = player.level()

                        context.source.sendSuccess(
                            {
                                Component.literal(
                                    buildString {
                                        append("Dimension: ")
                                        append(level.dimension().identifier())
                                        append("\nPosition: ")
                                        append(
                                            "%.2f, %.2f, %.2f"
                                                .format(
                                                    player.x,
                                                    player.y,
                                                    player.z,
                                                )
                                        )
                                        append("\nSeed: ")
                                        append(level.seed)
                                    }
                                )
                            },
                            false,
                        )

                        1
                    }
                )
                .then(
                    Commands.literal("dimensions").executes { context ->
                        val server = context.source.server
                        val registry = server.registryAccess().lookupOrThrow(Registries.LEVEL_STEM)

                        registry.keySet().forEach { key ->
                            context.source.sendSuccess(
                                {
                                    Component.literal(key.toString())
                                },
                                false,
                            )
                        }

                        1
                    }
                )
        )
    }
}
