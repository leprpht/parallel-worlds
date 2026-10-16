package com.leprpht.parallelworlds.mixin;

import com.leprpht.parallelworlds.ParallelWorldsPortals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {
    @Inject(method = "getPortalDestination", at = @At("HEAD"), cancellable = true)
    @SuppressWarnings("unused")
    @SuppressWarnings("unused")
    private void overrideDestination(
            ServerLevel world,
            Entity entity,
            BlockPos pos,
            CallbackInfoReturnable<TeleportTransition> cir) {
        var destination = ParallelWorldsPortals.INSTANCE.findDestination(world, pos);
        if (destination == null) {
            return;
        }

        cir.setReturnValue(
                new TeleportTransition(
                        destination.getWorld(),
                        Vec3.atCenterOf(destination.getPosition()),
                        entity.getDeltaMovement(),
                        entity.getYRot(),
                        entity.getXRot(),
                        TeleportTransition.PLAY_PORTAL_SOUND));
    }
}
