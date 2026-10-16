package com.leprpht.parallelworlds.mixin

import com.leprpht.parallelworlds.ParallelWorldsPortals
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.block.NetherPortalBlock
import net.minecraft.world.level.portal.TeleportTransition
import net.minecraft.world.phys.Vec3
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(NetherPortalBlock::class)
abstract class NetherPortalBlockMixin {

    @Inject(
        method = ["getPortalDestination"],
        at = [At("HEAD")],
        cancellable = true,
    )
    private fun overrideDestination(
        world: ServerLevel,
        entity: Entity,
        pos: BlockPos,
        cir: CallbackInfoReturnable<TeleportTransition?>,
    ) {
        val destination =
            ParallelWorldsPortals.findDestination(
                world,
                pos,
            ) ?: return

        cir.returnValue =
            TeleportTransition(
                destination.world,
                Vec3.atCenterOf(destination.position),
                entity.deltaMovement,
                entity.yRot,
                entity.xRot,
                TeleportTransition.PLAY_PORTAL_SOUND,
            )
    }
}
