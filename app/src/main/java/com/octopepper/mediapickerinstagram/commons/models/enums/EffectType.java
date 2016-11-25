package com.octopepper.mediapickerinstagram.commons.models.enums;

public enum EffectType {
    None("None"),
    Tonal("Tonal"),
    BlackAndWhite("BlackAndWhite"),
    Brightness("Brightness"),
    Contrast("Contrast"),
    CrossProcess("CrossProcess"),
    Documentary("Documentary"),
    FillLight("FillLight"),
    Grain("Grain"),
    GrayScale("GrayScale"),
    Lomoish("Lomoish"),
    Posterize("Posterize"),
    Saturate("Saturate"),
    Sepia("Sepia"),
    Temperature("Temperature"),
    Vignette("Vignette");

    private String name;

    EffectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
