package com.openai.knifesteel;

import java.util.*;

public final class SteelData {
    private SteelData() {}

    private static Steel s(String n, String f, String h, float t, float e, float c, float sh, String note) {
        return new Steel(n, f, h, t, e, c, sh, note);
    }

    public static List<Steel> all() {
        List<Steel> s = new ArrayList<>();

        s.add(s("8Cr13MoV","Stainless","57–59",6.5f,4.0f,7.0f,9.0f,"Easy to sharpen and inexpensive; moderate edge retention."));
        s.add(s("1095","Carbon steel","56–60",8.0f,4.5f,1.5f,9.0f,"Simple carbon steel with good toughness and very easy sharpening; requires corrosion care."));
        s.add(s("14C28N","Nitrogen stainless","58–62",8.5f,5.2f,9.0f,9.0f,"Excellent toughness, corrosion resistance and sharpenability; takes a very keen edge."));
        s.add(s("154CM","Stainless","58–61",6.0f,6.2f,7.2f,7.0f,"Well-balanced classic stainless with straightforward maintenance."));
        s.add(s("440C","Stainless","57–60",5.5f,5.5f,8.0f,7.0f,"Classic stainless with good corrosion resistance and moderate wear resistance."));
        s.add(s("52100","Carbon bearing steel","60–64",9.0f,6.0f,2.0f,8.5f,"Fine carbide structure, excellent toughness and keen-edge behavior; not stainless."));
        s.add(s("80CrV2","Carbon tool steel","57–61",9.5f,4.8f,1.5f,9.0f,"Very tough simple tool steel favored for hard-use blades; needs corrosion protection."));
        s.add(s("8670","Carbon alloy steel","57–60",10.0f,4.2f,1.5f,9.5f,"Exceptionally tough steel used where impact resistance matters most."));
        s.add(s("AEB-L","Stainless","59–63",9.5f,5.0f,8.5f,9.0f,"Very high toughness for a stainless steel with excellent sharpenability and fine edge behavior."));
        s.add(s("AUS-8","Stainless","57–59",7.0f,4.5f,8.0f,9.0f,"Easy-maintenance stainless with modest edge retention and good toughness."));
        s.add(s("CPM 10V","PM tool steel","62–65",4.5f,9.7f,2.0f,2.5f,"Extreme wear resistance and slicing edge retention; difficult sharpening and low corrosion resistance."));
        s.add(s("CPM 15V","PM tool steel","64–66",3.5f,10.0f,1.5f,1.5f,"Among the highest wear-resistant knife steels; specialized and demanding to sharpen."));
        s.add(s("CPM 20CV","PM stainless","59–62",5.0f,8.2f,9.0f,4.0f,"High wear resistance and corrosion resistance; close relative of M390 and 204P."));
        s.add(s("CPM 3V","PM tool steel","58–61",9.8f,6.2f,3.0f,6.5f,"Extremely tough PM steel suited to impact and hard-use applications."));
        s.add(s("CPM 4V","PM tool steel","62–64",8.0f,8.2f,2.5f,4.8f,"Excellent edge stability, toughness and wear resistance for demanding cutting."));
        s.add(s("CPM CruWear","PM tool steel","61–64",8.0f,7.5f,4.0f,6.0f,"Excellent balance of toughness, edge retention and sharpenability for EDC and hard use."));
        s.add(s("CPM M4","High-speed PM tool steel","62–65",6.5f,8.8f,2.0f,3.5f,"High wear resistance with useful toughness; excellent cutter if corrosion is managed."));
        s.add(s("CPM MagnaCut","PM stainless","61–64",8.5f,7.5f,9.5f,6.5f,"Exceptionally balanced stainless combining toughness, corrosion resistance and strong edge retention."));
        s.add(s("CPM Rex 45","High-speed PM tool steel","62–66",5.5f,8.8f,2.0f,3.0f,"High hardness and wear resistance with better toughness than many ultra-hard alternatives."));
        s.add(s("CPM S110V","PM stainless","60–63",3.8f,9.4f,9.0f,2.5f,"Very high wear and corrosion resistance; demanding to sharpen and less forgiving of edge damage."));
        s.add(s("CPM S125V","PM stainless","60–63",2.5f,9.8f,8.5f,1.8f,"Extremely high carbide volume and wear resistance; difficult sharpening and low toughness."));
        s.add(s("CPM S30V","PM stainless","59–61",6.0f,6.8f,7.5f,6.0f,"Classic premium stainless with a balanced all-round profile."));
        s.add(s("CPM S35VN","PM stainless","59–62",7.0f,6.5f,7.8f,6.8f,"Balanced stainless emphasizing improved toughness and easier sharpening than S30V."));
        s.add(s("CPM S45VN","PM stainless","59–62",6.0f,7.0f,8.3f,6.0f,"Refinement of S30V with improved corrosion resistance and balanced performance."));
        s.add(s("CPM S60V","PM stainless","59–61",4.0f,8.5f,8.5f,3.0f,"High wear stainless with strong edge retention but more difficult sharpening."));
        s.add(s("CPM S90V","PM stainless","59–62",5.0f,9.2f,8.5f,2.8f,"Excellent high-end edge retention with better toughness than some ultra-wear-resistant steels."));
        s.add(s("CPM SPY27","PM stainless","60–62",7.0f,6.5f,8.0f,7.5f,"Balanced stainless designed for practical EDC use with friendly sharpening."));
        s.add(s("CTS-204P","PM stainless","59–62",5.0f,8.2f,9.0f,4.0f,"Closely related to M390 and 20CV; emphasizes wear and corrosion resistance."));
        s.add(s("CTS-BD1N","Nitrogen stainless","59–61",7.0f,5.5f,8.5f,8.0f,"Easy-maintenance stainless with good toughness and corrosion resistance."));
        s.add(s("CTS-XHP","Powder stainless","60–63",5.0f,7.5f,7.0f,5.5f,"Good edge retention and hardness with moderate stainless behavior."));
        s.add(s("D2","Tool steel / semi-stainless","59–62",5.5f,7.0f,4.5f,5.5f,"High wear resistance at modest cost; corrosion resistance is only moderate."));
        s.add(s("Elmax","PM stainless","59–62",6.0f,7.7f,8.8f,5.0f,"Balanced premium stainless with good wear resistance and corrosion resistance."));
        s.add(s("H1","Precipitation-hardened stainless","57–59",8.0f,4.3f,10.0f,8.5f,"Exceptional corrosion resistance, especially for marine use."));
        s.add(s("H2","Corrosion-proof stainless","58–60",8.0f,4.7f,10.0f,8.5f,"Marine-oriented successor to H1 with outstanding corrosion resistance."));
        s.add(s("K390","High-alloy tool steel","63–66",6.0f,9.5f,2.0f,4.0f,"Exceptional wear resistance and edge retention; not stainless."));
        s.add(s("LC200N","Nitrogen stainless","57–60",8.0f,5.0f,10.0f,8.5f,"Near-immunity to corrosion with good toughness; ideal around salt water."));
        s.add(s("M390","PM stainless","59–62",5.0f,8.2f,9.2f,4.0f,"Premium stainless emphasizing wear and corrosion resistance."));
        s.add(s("Maxamet","High-speed tool steel","66–70",2.8f,10.0f,1.5f,1.5f,"Extreme hardness and wear resistance; low corrosion resistance and very difficult sharpening."));
        s.add(s("N690","Stainless","58–61",5.5f,5.8f,8.5f,7.0f,"Conventional premium stainless with good corrosion resistance and easy maintenance."));
        s.add(s("Nitro-V","Nitrogen stainless","58–62",8.0f,5.0f,8.8f,8.8f,"Tough, corrosion-resistant stainless with excellent ease of sharpening."));
        s.add(s("Rex 121","High-speed PM tool steel","68–71",1.5f,10.0f,1.0f,1.0f,"Extreme hardness and wear resistance at the cost of toughness and sharpenability."));
        s.add(s("SK5","Carbon tool steel","57–60",8.5f,4.5f,1.5f,9.0f,"Tough, simple carbon tool steel that is easy to sharpen but rust-prone."));
        s.add(s("Vanax","Nitrogen PM stainless","59–61",7.0f,7.0f,10.0f,5.5f,"Outstanding corrosion resistance with good edge retention and useful toughness."));
        s.add(s("VG-10","Stainless","59–61",6.5f,5.8f,8.0f,7.5f,"Proven all-round stainless steel with straightforward maintenance."));
        s.add(s("Z-Max","High-speed PM tool steel","66–69",2.0f,10.0f,1.0f,1.2f,"Ultra-high wear resistance and hardness; specialized and difficult to sharpen."));
        s.add(s("ZDP-189","High-carbon stainless","64–67",3.0f,9.0f,6.0f,2.5f,"Very high hardness and wear resistance, but comparatively brittle and demanding to sharpen."));

        Collections.sort(s, Comparator.comparing(a -> a.name.toLowerCase(Locale.US)));
        return s;
    }
}
