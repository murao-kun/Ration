package net.murao.ration.capabilities;

import net.minecraft.nbt.CompoundTag;

public class PlayerDiversity {
    private int diversity;
    private final int MIN_DIVERSITY = 0;
    private final int MAX_DIVERSITY = 16;

    public int getDiversity() {
        return diversity;
    }

    public void addDiversity(int add) {
        this.diversity = Math.min(diversity + add, MAX_DIVERSITY);
    }

    public void subDiversity(int sub) {
        this.diversity = Math.max(diversity + sub, MIN_DIVERSITY);
    }

    public void copyFrom(PlayerDiversity source) {
        this.diversity = source.diversity;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("diversity", diversity);
    }

    public void loadNBTData(CompoundTag nbt) {
        diversity = nbt.getInt("diversity");
    }
}
