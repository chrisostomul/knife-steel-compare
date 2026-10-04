package com.openai.knifesteel;

public final class Steel {
    public final String name, family, hrc, note;
    public final float toughness, edge, corrosion, sharpening;

    public Steel(String name, String family, String hrc,
                 float toughness, float edge, float corrosion, float sharpening,
                 String note) {
        this.name = name;
        this.family = family;
        this.hrc = hrc;
        this.toughness = toughness;
        this.edge = edge;
        this.corrosion = corrosion;
        this.sharpening = sharpening;
        this.note = note;
    }

    public float[] chartValues() {
        // Top, bottom-right, bottom-left
        return new float[]{edge, toughness, corrosion};
    }

    @Override public String toString() { return name; }
}
