package com.octopepper.mediapickerinstagram.commons.modules;

import com.octopepper.mediapickerinstagram.commons.models.enums.EffectType;

import java.util.ArrayList;
import java.util.List;

public class EffectModule {

    public EffectModule() {
    }

    public List<EffectType> getEffectTypes() {
        List<EffectType> effectTypes = new ArrayList<>();
        effectTypes.add(EffectType.None);
        effectTypes.add(EffectType.BlackAndWhite);
        effectTypes.add(EffectType.Contrast);
        effectTypes.add(EffectType.CrossProcess);
        effectTypes.add(EffectType.Documentary);
        effectTypes.add(EffectType.DuoTone);
        effectTypes.add(EffectType.FillLight);
        effectTypes.add(EffectType.Fisheye);
        effectTypes.add(EffectType.Grain);
        effectTypes.add(EffectType.GrayScale);
        effectTypes.add(EffectType.Posterize);
        effectTypes.add(EffectType.Saturate);
        effectTypes.add(EffectType.Sepia);
        effectTypes.add(EffectType.Sharpen);
        effectTypes.add(EffectType.Temperature);
        effectTypes.add(EffectType.Vignette);
        return effectTypes;
    }
}