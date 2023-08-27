package net.murao.ration.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.murao.ration.Ration;
import net.murao.ration.diversity.PlayerDiversity;
import net.murao.ration.diversity.PlayerDiversityProvider;

@Mod.EventBusSubscriber(modid = Ration.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerDiversityProvider.PLAYER_DIVERSITY).isPresent()) {
                event.addCapability(new ResourceLocation(Ration.MOD_ID, "properties"), new PlayerDiversityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerDiversityProvider.PLAYER_DIVERSITY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerDiversityProvider.PLAYER_DIVERSITY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerDiversity.class);
    }
}
