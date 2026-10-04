package com.openai.knifesteel;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import java.util.*;

public class RadarView extends View {
    private static final float SCALE_MAX = 12f;
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final List<Steel> steels = new ArrayList<>();
    private int[] colors = new int[0];
    private final String[] labels = {
        "Edge retention", "Toughness", "Corrosion resistance"
    };

    public RadarView(Context c) { super(c); init(); }
    public RadarView(Context c, AttributeSet at) { super(c, at); init(); }

    private void init() {
        p.setTypeface(Typeface.create("sans", Typeface.NORMAL));
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setSteels(List<Steel> newSteels, int[] newColors) {
        steels.clear();
        steels.addAll(newSteels);
        colors = Arrays.copyOf(newColors, newColors.length);
        invalidate();
    }

    @Override protected void onDraw(Canvas c) {
        super.onDraw(c);

        float w = getWidth(), h = getHeight();
        float cx = w / 2f;
        float cy = h * 0.49f;
        float r = Math.min(w * 0.39f, h * 0.34f);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(dp(1.2f));
        p.setColor(Color.rgb(210,210,210));
        for (int ring = 2; ring <= 12; ring += 2) {
            drawTriangle(c, cx, cy, r * ring / SCALE_MAX);
        }

        p.setColor(Color.rgb(185,185,185));
        for (int i = 0; i < 3; i++) {
            float[] q = point(cx, cy, r, i, 1f);
            c.drawLine(cx, cy, q[0], q[1], p);
        }

        p.setTextSize(12 * getResources().getDisplayMetrics().scaledDensity);
        p.setTypeface(Typeface.create("sans", Typeface.BOLD));
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.DKGRAY);

        drawCenteredLabel(c, labels[0], cx, cy - r - dp(18));
        drawRightLabel(c, labels[1], cx + r * 0.84f, cy + r * 0.61f + dp(24), w);
        drawLeftLabel(c, labels[2], cx - r * 0.84f, cy + r * 0.61f + dp(24));

        p.setTypeface(Typeface.create("sans", Typeface.NORMAL));
        p.setTextSize(10 * getResources().getDisplayMetrics().scaledDensity);
        p.setColor(Color.GRAY);
        String scale = "0–12 rating scale";
        c.drawText(scale, cx - p.measureText(scale)/2f, h - dp(8), p);

        for (int i = 0; i < steels.size() && i < colors.length; i++) {
            drawData(c, cx, cy, r, steels.get(i).chartValues(), colors[i]);
        }
    }

    private void drawCenteredLabel(Canvas c, String text, float x, float y) {
        c.drawText(text, x - p.measureText(text)/2f, y, p);
    }

    private void drawRightLabel(Canvas c, String text, float x, float y, float width) {
        float tw = p.measureText(text);
        float tx = Math.min(x - tw * 0.35f, width - tw - dp(4));
        c.drawText(text, tx, y, p);
    }

    private void drawLeftLabel(Canvas c, String text, float x, float y) {
        float tw = p.measureText(text);
        float tx = Math.max(dp(4), x - tw * 0.65f);
        c.drawText(text, tx, y, p);
    }

    private void drawData(Canvas c, float cx, float cy, float r, float[] v, int color) {
        Path path = new Path();
        for (int i = 0; i < 3; i++) {
            float scaled = Math.max(0f, Math.min(SCALE_MAX, v[i])) / SCALE_MAX;
            float[] q = point(cx, cy, r, i, scaled);
            if (i == 0) path.moveTo(q[0], q[1]); else path.lineTo(q[0], q[1]);
        }
        path.close();

        p.setColor((color & 0x00FFFFFF) | 0x22000000);
        p.setStyle(Paint.Style.FILL);
        c.drawPath(path, p);

        p.setColor(color);
        p.setStrokeWidth(dp(3f));
        p.setStyle(Paint.Style.STROKE);
        c.drawPath(path, p);
    }

    private void drawTriangle(Canvas c, float cx, float cy, float r) {
        Path path = new Path();
        for (int i = 0; i < 3; i++) {
            float[] q = point(cx, cy, r, i, 1f);
            if (i == 0) path.moveTo(q[0], q[1]); else path.lineTo(q[0], q[1]);
        }
        path.close();
        c.drawPath(path, p);
    }

    private float[] point(float cx, float cy, float r, int i, float f) {
        double angle;
        if (i == 0) angle = -Math.PI / 2.0;          // top: edge retention
        else if (i == 1) angle = Math.PI / 6.0;      // bottom-right: toughness
        else angle = 5.0 * Math.PI / 6.0;            // bottom-left: corrosion

        return new float[]{
            cx + (float)Math.cos(angle) * r * f,
            cy + (float)Math.sin(angle) * r * f
        };
    }

    private float dp(float n) {
        return n * getResources().getDisplayMetrics().density;
    }
}
