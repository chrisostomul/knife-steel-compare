package com.openai.knifesteel;

import android.app.*;
import android.os.Bundle;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.text.*;
import android.text.style.ForegroundColorSpan;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    private static final String PREFS="knife_steel_prefs";
    private static final String PREF_FAVORITES="favorites";
    private static final int SLOT_COUNT=5;

    private final int[] chartColors = {
        Color.rgb(33,150,243),
        Color.rgb(244,81,30),
        Color.rgb(67,160,71),
        Color.rgb(123,31,162),
        Color.rgb(229,57,53)
    };

    private List<Steel> steels;
    private final Spinner[] selectors = new Spinner[SLOT_COUNT];
    private final Button[] stars = new Button[SLOT_COUNT];
    private LinearLayout legend, stats;
    private RadarView radar;
    private Set<String> favorites;
    private boolean rebuilding=false;

    private static final class SteelOption {
        final Steel steel;
        final boolean favorite;
        SteelOption(Steel steel, boolean favorite) { this.steel=steel; this.favorite=favorite; }
        @Override public String toString() {
            if (steel==null) return "— None —";
            return (favorite ? "★ " : "") + steel.name;
        }
    }

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        steels = SteelData.all();
        favorites = new HashSet<>(getSharedPreferences(PREFS,MODE_PRIVATE).getStringSet(PREF_FAVORITES, Collections.emptySet()));
        buildUi();
    }

    private void buildUi() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(16),dp(18),dp(16),dp(30));
        root.setBackgroundColor(Color.rgb(246,246,246));
        scroll.addView(root);

        TextView title = text("Knife Steel Compare",28,Typeface.BOLD);
        root.addView(title);
        TextView sub = text("Compare up to 5 steels • offline • practical 1–10 estimates",14,Typeface.NORMAL);
        sub.setTextColor(Color.DKGRAY);
        sub.setPadding(0,dp(3),0,dp(14));
        root.addView(sub);

        for(int i=0;i<SLOT_COUNT;i++) {
            final int slot=i;
            LinearLayout row=new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(0,0,0,dp(8));

            TextView dot=text("●",22,Typeface.BOLD);
            dot.setTextColor(chartColors[i]);
            dot.setGravity(Gravity.CENTER);
            row.addView(dot,new LinearLayout.LayoutParams(dp(34),dp(50)));

            selectors[i]=new Spinner(this);
            selectors[i].setBackgroundColor(Color.WHITE);
            selectors[i].setPadding(dp(8),0,dp(8),0);
            row.addView(selectors[i],new LinearLayout.LayoutParams(0,dp(50),1f));

            stars[i]=new Button(this);
            stars[i].setText("☆");
            stars[i].setTextSize(24);
            stars[i].setAllCaps(false);
            stars[i].setPadding(0,0,0,0);
            stars[i].setOnClickListener(v -> toggleFavorite(slot));
            row.addView(stars[i],new LinearLayout.LayoutParams(dp(52),dp(50)));

            selectors[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override public void onItemSelected(AdapterView<?> p,View v,int pos,long id) {
                    if(!rebuilding) refresh();
                }
                @Override public void onNothingSelected(AdapterView<?> p) {}
            });
            root.addView(row);
        }

        TextView legendTitle=text("Chart legend",15,Typeface.BOLD);
        legendTitle.setPadding(0,dp(3),0,dp(5));
        root.addView(legendTitle);

        legend=new LinearLayout(this);
        legend.setOrientation(LinearLayout.VERTICAL);
        legend.setPadding(dp(8),dp(7),dp(8),dp(7));
        GradientDrawable legendBg=new GradientDrawable();
        legendBg.setColor(Color.WHITE);
        legendBg.setCornerRadius(dp(10));
        legend.setBackground(legendBg);
        root.addView(legend);

        radar=new RadarView(this);
        root.addView(radar,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dp(390)));

        stats=new LinearLayout(this);
        stats.setOrientation(LinearLayout.VERTICAL);
        root.addView(stats);

        String[] initial={"CPM CruWear","CPM MagnaCut",null,null,null};
        rebuildAdapters(initial);
        setContentView(scroll);
        refresh();
    }

    private void toggleFavorite(int slot) {
        Steel s=selectedSteel(slot);
        if(s==null) return;
        if(favorites.contains(s.name)) favorites.remove(s.name); else favorites.add(s.name);
        getSharedPreferences(PREFS,MODE_PRIVATE).edit().putStringSet(PREF_FAVORITES,new HashSet<>(favorites)).apply();
        String[] keep=currentNames();
        rebuildAdapters(keep);
        refresh();
    }

    private String[] currentNames() {
        String[] out=new String[SLOT_COUNT];
        for(int i=0;i<SLOT_COUNT;i++) {
            Steel s=selectedSteel(i);
            out[i]=s==null?null:s.name;
        }
        return out;
    }

    private void rebuildAdapters(String[] keep) {
        rebuilding=true;
        List<SteelOption> opts=options();
        for(int i=0;i<SLOT_COUNT;i++) {
            ArrayAdapter<SteelOption> ad=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,new ArrayList<>(opts));
            selectors[i].setAdapter(ad);
            String wanted = keep!=null && i<keep.length ? keep[i] : null;
            selectors[i].setSelection(indexOfOption(opts,wanted),false);
        }
        rebuilding=false;
    }

    private List<SteelOption> options() {
        List<Steel> fav=new ArrayList<>(), rest=new ArrayList<>();
        for(Steel s:steels) {
            if(favorites.contains(s.name)) fav.add(s); else rest.add(s);
        }
        Comparator<Steel> byName=Comparator.comparing(a -> a.name.toLowerCase(Locale.US));
        fav.sort(byName); rest.sort(byName);

        List<SteelOption> out=new ArrayList<>();
        out.add(new SteelOption(null,false));
        for(Steel s:fav) out.add(new SteelOption(s,true));
        for(Steel s:rest) out.add(new SteelOption(s,false));
        return out;
    }

    private int indexOfOption(List<SteelOption> opts,String name) {
        if(name==null) return 0;
        for(int i=1;i<opts.size();i++) if(opts.get(i).steel.name.equals(name)) return i;
        return 0;
    }

    private Steel selectedSteel(int slot) {
        Object o=selectors[slot].getSelectedItem();
        return o instanceof SteelOption ? ((SteelOption)o).steel : null;
    }

    private void refresh() {
        List<Steel> active=new ArrayList<>();
        List<Integer> activeColors=new ArrayList<>();
        legend.removeAllViews();

        for(int i=0;i<SLOT_COUNT;i++) {
            Steel s=selectedSteel(i);
            stars[i].setText(s!=null && favorites.contains(s.name) ? "★" : "☆");
            stars[i].setEnabled(s!=null);

            if(s!=null) {
                active.add(s);
                activeColors.add(chartColors[i]);

                SpannableString ss=new SpannableString("●  "+s.name+(favorites.contains(s.name)?"  ★":""));
                ss.setSpan(new ForegroundColorSpan(chartColors[i]),0,1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                TextView l=text("",14,Typeface.BOLD);
                l.setText(ss);
                l.setPadding(dp(3),dp(3),0,dp(3));
                legend.addView(l);
            }
        }

        if(active.isEmpty()) {
            TextView empty=text("Select at least one steel.",14,Typeface.NORMAL);
            empty.setTextColor(Color.GRAY);
            legend.addView(empty);
        }

        int[] colors=new int[activeColors.size()];
        for(int i=0;i<colors.length;i++) colors[i]=activeColors.get(i);
        radar.setSteels(active,colors);

        stats.removeAllViews();
        if(active.isEmpty()) return;

        TextView heading=text("Scores",18,Typeface.BOLD);
        heading.setPadding(0,dp(6),0,dp(5));
        stats.addView(heading);

        addScoreRow("Edge retention",active,colors,0);
        addScoreRow("Toughness",active,colors,1);
        addScoreRow("Corrosion",active,colors,2);
        addScoreRow("Sharpenability",active,colors,3);

        TextView notesHead=text("Details",18,Typeface.BOLD);
        notesHead.setPadding(0,dp(16),0,dp(6));
        stats.addView(notesHead);

        for(int i=0;i<active.size();i++) {
            Steel s=active.get(i);
            TextView n=text(s.name+"  •  "+s.hrc+" HRC\n"+s.note,14,Typeface.NORMAL);
            n.setPadding(dp(10),dp(9),dp(10),dp(9));
            n.setTextColor(Color.rgb(45,45,45));

            GradientDrawable bg=new GradientDrawable();
            bg.setColor(Color.WHITE);
            bg.setCornerRadius(dp(9));
            bg.setStroke(dp(2),colors[i]);
            n.setBackground(bg);

            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,0,dp(8));
            stats.addView(n,lp);
        }

        TextView foot=text("Scores are comparative estimates, not laboratory constants. Heat treatment, hardness, geometry, edge angle and sharpening finish can materially change real-world performance.",12,Typeface.NORMAL);
        foot.setTextColor(Color.GRAY);
        foot.setPadding(0,dp(6),0,0);
        stats.addView(foot);
    }

    private void addScoreRow(String label,List<Steel> active,int[] colors,int which) {
        LinearLayout row=new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0,dp(5),0,dp(5));

        TextView lab=text(label,14,Typeface.BOLD);
        row.addView(lab,new LinearLayout.LayoutParams(0,dp(38),1.55f));

        for(int i=0;i<active.size();i++) {
            Steel s=active.get(i);
            float v;
            if(which==0) v=s.edge;
            else if(which==1) v=s.toughness;
            else if(which==2) v=s.corrosion;
            else v=s.sharpening;
            TextView score=text(String.format(Locale.US,"%.1f",v),14,Typeface.BOLD);
            score.setTextColor(Color.WHITE);
            score.setGravity(Gravity.CENTER);
            GradientDrawable bg=new GradientDrawable();
            bg.setColor(colors[i]);
            bg.setCornerRadius(dp(6));
            score.setBackground(bg);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0,dp(34),0.62f);
            lp.setMargins(dp(3),0,0,0);
            row.addView(score,lp);
        }
        stats.addView(row);
    }

    private TextView text(String s,int sp,int style) {
        TextView t=new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(Color.rgb(32,33,36));
        t.setTypeface(Typeface.create("sans",style));
        return t;
    }

    private int dp(int n) {
        return Math.round(n*getResources().getDisplayMetrics().density);
    }
}
