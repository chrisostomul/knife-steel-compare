package com.openai.knifesteel;

public final class Steel {
    public final String name, family, hrc, note;
    public final float toughness, edge, corrosion, sharpening, fineEdge;

    public Steel(String name, String family, String hrc,
                 float toughness, float edge, float corrosion, float sharpening, float fineEdge,
                 String note) {
        this.name = name; this.family = family; this.hrc = hrc;
        this.toughness = toughness; this.edge = edge; this.corrosion = corrosion;
        this.sharpening = sharpening; this.fineEdge = fineEdge; this.note = note;
    }

    public float[] values() { return new float[]{toughness, edge, corrosion, sharpening, fineEdge}; }
    @Override public String toString() { return name; }
}
