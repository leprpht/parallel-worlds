package com.leprpht.parallelworlds.mixin;

import com.leprpht.parallelworlds.ParallelWorldsPortals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class NetherPortalBlockMixin {
    @Inject(method = "getPortalDestination", at = @At("HEAD"), cancellable = true)
    private void overrideDestination(
            ServerLevel world,
            Entity entity,
            BlockPos pos,
            CallbackInfoReturnable<DimensionTransition> cir) {
        ParallelWorldsPortals.PortalDestination destination =
                ParallelWorldsPortals.INSTANCE.findDestination(world, pos);
        if (destination == null) {
            return;
        }

        cir.setReturnValue(
                new DimensionTransition(
                        destination.getWorld(),
                        Vec3.atCenterOf(destination.getPosition()),
                        entity.getDeltaMovement(),
                        entity.getYRot(),
                        entity.getXRot(),
                        DimensionTransition.PLAY_PORTAL_SOUND));
    }
}
