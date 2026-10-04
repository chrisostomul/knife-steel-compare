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

    public float[] values() {
        // North, East, South, West
        return new float[]{edge, toughness, corrosion, sharpening};
    }

    @Override public String toString() { return name; }
}
