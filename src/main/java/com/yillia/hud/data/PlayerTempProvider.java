package com.yillia.hud.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerTempProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerTemp> PLAYER_TEMP = CapabilityManager.get(new CapabilityToken<PlayerTemp>() { });

    private PlayerTemp temp = null;

    private final LazyOptional<PlayerTemp> optional = LazyOptional.of(this::createPlayerTemp);

    private PlayerTemp createPlayerTemp() {
        if(this.temp == null) {
            this.temp = new PlayerTemp();
        }

        return this.temp;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_TEMP) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerTemp().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerTemp().loadNBTData(nbt);
    }
}
