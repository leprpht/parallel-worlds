package com.leprpht.parallelworlds.mixin;

import com.leprpht.parallelworlds.ParallelWorldsPortals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Entity.class)
public abstract class NetherPortalBlockMixin {
    @Shadow protected BlockPos portalEntranceBlock;
    @Shadow public Level level;

    private ParallelWorldsPortals.PortalDestination customDestination() {
        if (!(level instanceof ServerLevel) || portalEntranceBlock == null) {
            return null;
        }
        ServerLevel serverLevel = (ServerLevel) level;
        return ParallelWorldsPortals.INSTANCE.findDestination(serverLevel, portalEntranceBlock);
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
        ParallelWorldsPortals.PortalDestination destination = customDestination();
        return destination == null ? original : destination.getWorld();
    }
}
