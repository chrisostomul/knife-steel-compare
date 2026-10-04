package com.openai.knifesteel;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class RadarView extends View {
    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Steel a, b;
    private final String[] labels = {"Toughness", "Edge retention", "Corrosion", "Sharpening", "Fine edge"};

    public RadarView(Context c) { super(c); init(); }
    public RadarView(Context c, AttributeSet at) { super(c, at); init(); }
    private void init() { p.setTypeface(Typeface.create("sans", Typeface.NORMAL)); }
    public void setSteels(Steel a, Steel b) { this.a=a; this.b=b; invalidate(); }

    @Override protected void onDraw(Canvas c) {
        super.onDraw(c);
        float w=getWidth(), h=getHeight();
        float cx=w/2f, cy=h/2f+8f, r=Math.min(w,h)*0.31f;
        p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(1.5f); p.setColor(Color.rgb(210,210,210));
        for(int ring=2; ring<=10; ring+=2) drawPoly(c,cx,cy,r*ring/10f,p);
        p.setColor(Color.rgb(190,190,190));
        for(int i=0;i<5;i++) { float[] pt=pt(cx,cy,r,i,1f); c.drawLine(cx,cy,pt[0],pt[1],p); }
        p.setTextSize(12*getResources().getDisplayMetrics().scaledDensity); p.setColor(Color.DKGRAY); p.setStyle(Paint.Style.FILL);
        for(int i=0;i<5;i++) {
            float[] q=pt(cx,cy,r*1.23f,i,1f); String t=labels[i]; float tw=p.measureText(t);
            c.drawText(t,q[0]-tw/2f,q[1]+5f,p);
        }
        if(a!=null) drawData(c,cx,cy,r,a.values(),Color.rgb(33,150,243));
        if(b!=null) drawData(c,cx,cy,r,b.values(),Color.rgb(244,81,30));
    }
    private void drawData(Canvas c,float cx,float cy,float r,float[] v,int color){
        Path path=new Path();
        for(int i=0;i<5;i++){ float[] q=pt(cx,cy,r,i,v[i]/10f); if(i==0)path.moveTo(q[0],q[1]); else path.lineTo(q[0],q[1]); }
        path.close(); p.setColor(color); p.setStrokeWidth(4f); p.setStyle(Paint.Style.STROKE); c.drawPath(path,p);
        p.setColor((color & 0x00FFFFFF)|0x30000000); p.setStyle(Paint.Style.FILL); c.drawPath(path,p);
    }
    private void drawPoly(Canvas c,float cx,float cy,float r,Paint p){
        Path path=new Path(); for(int i=0;i<5;i++){float[] q=pt(cx,cy,r,i,1f); if(i==0)path.moveTo(q[0],q[1]); else path.lineTo(q[0],q[1]);} path.close(); c.drawPath(path,p);
    }
    private float[] pt(float cx,float cy,float r,int i,float f){ double ang=-Math.PI/2 + i*2*Math.PI/5; return new float[]{cx+(float)Math.cos(ang)*r*f, cy+(float)Math.sin(ang)*r*f}; }
}
