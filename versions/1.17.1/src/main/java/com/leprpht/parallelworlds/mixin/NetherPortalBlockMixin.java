package com.leprpht.parallelworlds.mixin;

import com.leprpht.parallelworlds.ParallelWorldsPortals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class NetherPortalBlockMixin {
    @Shadow protected BlockPos portalEntrancePos;

    @Shadow
    public abstract Level level();

    @Shadow
    protected abstract PortalInfo findDimensionEntryPoint(ServerLevel destination);

    @Shadow
    public abstract Vec3 getDeltaMovement();

    @Shadow
    public abstract float getYRot();

    @Shadow
    public abstract float getXRot();

    private ParallelWorldsPortals.PortalDestination customDestination() {
        if (!(level() instanceof ServerLevel serverLevel) || portalEntrancePos == null) {
            return null;
        }
        return ParallelWorldsPortals.INSTANCE.findDestination(serverLevel, portalEntrancePos);
    }

    @ModifyArg(
            method = "changeDimension",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/entity/EntityType;create(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;"),
            index = 0)
    private Level useCustomDestination(Level original) {
        var destination = customDestination();
        return destination == null ? original : destination.getWorld();
    }

    @Redirect(
            method = "changeDimension",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/entity/Entity;findDimensionEntryPoint(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/level/portal/PortalInfo;"))
    private PortalInfo useCustomPortalPosition(Entity entity, ServerLevel originalDestination) {
        var destination = customDestination();
        if (destination == null) {
            return findDimensionEntryPoint(originalDestination);
        }

        return new PortalInfo(
                Vec3.atCenterOf(destination.getPosition()),
                getDeltaMovement(),
                getYRot(),
                getXRot());
    }
}
