package net.murao.ration.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class PlayerDiversityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerDiversity> PLAYER_DIVERSITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    private PlayerDiversity diversity = null;
    private final LazyOptional<PlayerDiversity> optional = LazyOptional.of(this::createPlayerDiversity);

    private PlayerDiversity createPlayerDiversity() {
        if(this.diversity == null) {
            this.diversity = new PlayerDiversity();
        }
        return this.diversity;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_DIVERSITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerDiversity().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerDiversity().loadNBTData(nbt);
    }
}
