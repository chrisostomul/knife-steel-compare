package com.openai.knifesteel;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import java.util.*;

public class RadarView extends View {
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final List<Steel> steels = new ArrayList<>();
    private int[] colors = new int[0];
    private final String[] labels = {
        "Edge retention", "Toughness", "Corrosion resistance", "Sharpenability"
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
        float cx = w / 2f, cy = h / 2f + 6f;
        float r = Math.min(w, h) * 0.31f;

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1.5f);
        p.setColor(Color.rgb(210,210,210));
        for (int ring = 2; ring <= 10; ring += 2) drawDiamond(c, cx, cy, r * ring / 10f);

        p.setColor(Color.rgb(188,188,188));
        for (int i = 0; i < 4; i++) {
            float[] q = point(cx, cy, r, i, 1f);
            c.drawLine(cx, cy, q[0], q[1], p);
        }

        p.setTextSize(12 * getResources().getDisplayMetrics().scaledDensity);
        p.setTypeface(Typeface.create("sans", Typeface.BOLD));
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.DKGRAY);

        float[][] lp = {
            {cx, cy-r*1.22f},
            {cx+r*1.32f, cy},
            {cx, cy+r*1.25f},
            {cx-r*1.32f, cy}
        };
        for (int i=0;i<4;i++) {
            float tw=p.measureText(labels[i]);
            float x=lp[i][0]-tw/2f;
            float y=lp[i][1]+5f;
            if (i==1) x=Math.min(x,w-tw-4);
            if (i==3) x=Math.max(x,4);
            c.drawText(labels[i],x,y,p);
        }

        for (int i=0; i<steels.size() && i<colors.length; i++) {
            drawData(c,cx,cy,r,steels.get(i).values(),colors[i]);
        }
    }

    private void drawData(Canvas c,float cx,float cy,float r,float[] v,int color){
        Path path=new Path();
        for(int i=0;i<4;i++){
            float[] q=point(cx,cy,r,i,v[i]/10f);
            if(i==0) path.moveTo(q[0],q[1]); else path.lineTo(q[0],q[1]);
        }
        path.close();

        p.setColor(color);
        p.setStrokeWidth(4f);
        p.setStyle(Paint.Style.STROKE);
        c.drawPath(path,p);

        p.setColor((color & 0x00FFFFFF) | 0x22000000);
        p.setStyle(Paint.Style.FILL);
        c.drawPath(path,p);
    }

    private void drawDiamond(Canvas c,float cx,float cy,float r){
        Path path=new Path();
        for(int i=0;i<4;i++){
            float[] q=point(cx,cy,r,i,1f);
            if(i==0) path.moveTo(q[0],q[1]); else path.lineTo(q[0],q[1]);
        }
        path.close();
        c.drawPath(path,p);
    }

    private float[] point(float cx,float cy,float r,int i,float f){
        switch(i){
            case 0: return new float[]{cx,cy-r*f}; // North: edge retention
            case 1: return new float[]{cx+r*f,cy}; // East: toughness
            case 2: return new float[]{cx,cy+r*f}; // South: corrosion
            default:return new float[]{cx-r*f,cy}; // West: sharpenability
        }
    }
}
