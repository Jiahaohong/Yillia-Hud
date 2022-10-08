package com.yillia.hud.datagen;

import com.yillia.hud.YilliaHud;
import com.yillia.hud.register.ModEffects;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangProvider {
    public static class EN_US extends LanguageProvider {
        public EN_US(DataGenerator gen) {
            super(gen, YilliaHud.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.addEffect(ModEffects.ENERGY_BOOST, "Energy Boost");
            this.addEffect(ModEffects.INSTANCE_ENERGY, "Instance Energy");
            this.addEffect(ModEffects.QUICK_RECOVERY, "Quick Recovery");
        }
    }

    public static class ZH_CN extends LanguageProvider {
        public ZH_CN(DataGenerator gen) {
            super(gen, YilliaHud.MOD_ID, "zh_cn");
        }

        @Override
        protected void addTranslations() {
            this.addEffect(ModEffects.ENERGY_BOOST, "精力提升");
            this.addEffect(ModEffects.INSTANCE_ENERGY, "瞬间充能");
            this.addEffect(ModEffects.QUICK_RECOVERY, "快速恢复");
        }
    }

}
