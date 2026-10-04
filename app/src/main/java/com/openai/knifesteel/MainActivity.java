package com.openai.knifesteel;

import android.app.*;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    private final int blue = Color.rgb(33,150,243), orange = Color.rgb(244,81,30);
    private List<Steel> steels;
    private Spinner left, right;
    private RadarView radar;
    private LinearLayout stats;
    private TextView notes;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        steels = SteelData.all();
        buildUi();
    }

    private void buildUi() {
        ScrollView scroll = new ScrollView(this); scroll.setFillViewport(true);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(18),dp(18),dp(18),dp(28)); root.setBackgroundColor(Color.rgb(246,246,246));
        scroll.addView(root);

        TextView title = text("Knife Steel Compare", 28, Typeface.BOLD); root.addView(title);
        TextView sub = text("Offline comparison • scores are practical 1–10 estimates", 14, Typeface.NORMAL); sub.setTextColor(Color.DKGRAY); sub.setPadding(0,dp(3),0,dp(16)); root.addView(sub);

        LinearLayout selectors = new LinearLayout(this); selectors.setOrientation(LinearLayout.HORIZONTAL);
        left = spinner(); right = spinner();
        selectors.addView(left,new LinearLayout.LayoutParams(0,dp(56),1));
        Space gap=new Space(this); selectors.addView(gap,new LinearLayout.LayoutParams(dp(10),1));
        selectors.addView(right,new LinearLayout.LayoutParams(0,dp(56),1)); root.addView(selectors);

        TextView legend = text("● Steel A      ● Steel B",14,Typeface.BOLD); legend.setPadding(0,dp(10),0,0); root.addView(legend);
        legend.setTextColor(Color.DKGRAY);

        radar = new RadarView(this); root.addView(radar,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dp(360)));

        stats = new LinearLayout(this); stats.setOrientation(LinearLayout.VERTICAL); root.addView(stats);
        notes = text("",14,Typeface.NORMAL); notes.setTextColor(Color.DKGRAY); notes.setPadding(0,dp(16),0,0); root.addView(notes);

        ArrayAdapter<Steel> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, steels);
        left.setAdapter(ad); right.setAdapter(ad);
        left.setSelection(indexOf("CPM CruWear")); right.setSelection(indexOf("CPM MagnaCut"));
        AdapterView.OnItemSelectedListener l = new AdapterView.OnItemSelectedListener(){ public void onItemSelected(AdapterView<?> p,View v,int pos,long id){ refresh(); } public void onNothingSelected(AdapterView<?> p){} };
        left.setOnItemSelectedListener(l); right.setOnItemSelectedListener(l); refresh();
        setContentView(scroll);
    }

    private Spinner spinner(){ Spinner s=new Spinner(this); s.setBackgroundColor(Color.WHITE); s.setPadding(dp(8),0,dp(8),0); return s; }
    private int indexOf(String n){ for(int i=0;i<steels.size();i++) if(steels.get(i).name.equals(n)) return i; return 0; }

    private void refresh(){
        if(left.getSelectedItem()==null || right.getSelectedItem()==null) return;
        Steel a=(Steel)left.getSelectedItem(), b=(Steel)right.getSelectedItem(); radar.setSteels(a,b); stats.removeAllViews();
        addRow("Toughness",a.toughness,b.toughness); addRow("Edge retention",a.edge,b.edge); addRow("Corrosion",a.corrosion,b.corrosion); addRow("Sharpening ease",a.sharpening,b.sharpening); addRow("Fine-edge stability",a.fineEdge,b.fineEdge);
        TextView h=text("Typical hardness:  "+a.name+"  "+a.hrc+" HRC     |     "+b.name+"  "+b.hrc+" HRC",14,Typeface.BOLD); h.setPadding(0,dp(14),0,0); stats.addView(h);
        notes.setText(a.name+": "+a.note+"\n\n"+b.name+": "+b.note+"\n\nNote: scores are comparative, not laboratory constants. Heat treatment, geometry and sharpening can materially change real-world performance.");
    }

    private void addRow(String label,float a,float b){
        LinearLayout row=new LinearLayout(this); row.setOrientation(LinearLayout.HORIZONTAL); row.setPadding(0,dp(7),0,dp(7));
        TextView lab=text(label,14,Typeface.BOLD); row.addView(lab,new LinearLayout.LayoutParams(0,dp(38),1.5f));
        TextView av=score(a,blue); row.addView(av,new LinearLayout.LayoutParams(0,dp(38),0.7f));
        TextView bv=score(b,orange); row.addView(bv,new LinearLayout.LayoutParams(0,dp(38),0.7f)); stats.addView(row);
    }
    private TextView score(float v,int color){ TextView t=text(String.format(Locale.US,"%.1f",v),16,Typeface.BOLD); t.setGravity(Gravity.CENTER); t.setTextColor(Color.WHITE); t.setBackgroundColor(color); return t; }
    private TextView text(String s,int sp,int style){ TextView t=new TextView(this); t.setText(s); t.setTextSize(sp); t.setTextColor(Color.rgb(32,33,36)); t.setTypeface(Typeface.create("sans",style)); return t; }
    private int dp(int n){ return Math.round(n*getResources().getDisplayMetrics().density); }
}
