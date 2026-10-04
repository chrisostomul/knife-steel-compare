package com.openai.knifesteel;

import java.util.*;

public final class SteelData {
    private SteelData() {}

    private static Steel s(String n, String f, String h, float t, float e, float c, float sh, String note) {
        return new Steel(n, f, h, t, e, c, sh, note);
    }

    public static List<Steel> all() {
        List<Steel> s = new ArrayList<>();

        // Ratings follow the Knife Steel Nerds scale as compiled by Metallurgy.info.
        // Sharpenability is a derived inverse score (12 - edge retention), not a lab measurement.
        s.add(s("8Cr13MoV","Stainless","56–59",6f,3f,7f,9f,"Budget stainless; easy to sharpen, moderate toughness and modest slicing edge retention."));
        s.add(s("1095","Carbon steel","58–62",4.5f,1.5f,0f,10.5f,"Simple carbon steel; very easy to sharpen but essentially no corrosion resistance."));
        s.add(s("14C28N","Nitrogen stainless","58–61",9f,3f,8.5f,9f,"Excellent toughness and corrosion resistance with modest CATRA-style edge retention."));
        s.add(s("154CM","Stainless","59–61",3.5f,4.5f,7f,7.5f,"Classic stainless with moderate edge retention and corrosion resistance."));
        s.add(s("440C","Stainless","58–60",3.5f,4.5f,7.5f,7.5f,"Classic high-carbon stainless; balanced but not especially tough."));
        s.add(s("52100","Carbon bearing steel","60–64",8.5f,2f,0.5f,10f,"Excellent toughness and fine-carbide behavior; very low corrosion resistance."));
        s.add(s("80CrV2","Carbon tool steel","60–63",8f,1.5f,0f,10.5f,"Very tough simple tool steel; low wear resistance and no meaningful corrosion resistance."));
        s.add(s("8670","Carbon alloy steel","58–62",10f,1.5f,0f,10.5f,"Extremely tough steel with low wear resistance; intended for impact-heavy use."));
        s.add(s("AEB-L","Stainless","59–62",9f,3f,7f,9f,"One of the toughest stainless steels; easy to sharpen and takes a keen edge."));
        s.add(s("AUS-8","Stainless","56–59",6f,3f,7f,9f,"Easy-maintenance stainless with modest edge retention."));
        s.add(s("CPM 10V","PM tool steel","60–63",5f,8.5f,4f,3.5f,"Very high slicing edge retention with moderate toughness; non-stainless."));
        s.add(s("CPM 15V","PM tool steel","60–63",3.5f,10f,4f,2f,"Extreme wear resistance and slicing edge retention; difficult to sharpen."));
        s.add(s("CPM 20CV","PM stainless","60–62",3.5f,6.5f,9f,5.5f,"M390-family stainless emphasizing wear and corrosion resistance."));
        s.add(s("CPM 3V","PM tool steel","60–64",9f,4.5f,5.5f,7.5f,"Extremely tough PM tool steel with moderate slicing edge retention."));
        s.add(s("CPM 4V","PM tool steel","60–63",7f,5f,4f,7f,"Balanced hard-use steel with high toughness and useful wear resistance."));
        s.add(s("CPM CruWear","PM tool steel","62–65",8f,5f,5.5f,7f,"High toughness with moderate CATRA-style edge retention; one of the best-balanced non-stainless steels."));
        s.add(s("CPM M4","High-speed PM tool steel","62–65",6.5f,6f,4f,6f,"Strong edge retention with useful toughness; low corrosion resistance."));
        s.add(s("CPM MagnaCut","PM stainless","60–64",7f,5f,9.5f,7f,"Exceptionally balanced stainless: high toughness, strong corrosion resistance and moderate CATRA edge retention."));
        s.add(s("CPM Rex 45","High-speed PM tool steel","63–67",4.5f,6f,4f,6f,"High-hardness high-speed steel; edge retention similar to M4 at typical ratings."));
        s.add(s("CPM S110V","PM stainless","60–62",3f,8f,9f,4f,"Very high wear and corrosion resistance; lower toughness and demanding sharpening."));
        s.add(s("CPM S125V","PM stainless","60–62",2.5f,9.5f,7.5f,2.5f,"Extreme stainless wear resistance with low toughness."));
        s.add(s("CPM S30V","PM stainless","58–61",4f,6f,7.5f,6f,"Benchmark premium stainless with good edge retention and above-average corrosion resistance."));
        s.add(s("CPM S35VN","PM stainless","58–61",5f,5f,7.5f,7f,"Balanced stainless with improved toughness and sharpenability over higher-wear grades."));
        s.add(s("CPM S45VN","PM stainless","59–61",4f,5.5f,8f,6.5f,"Balanced stainless with improved corrosion resistance over S30V/S35VN."));
        s.add(s("CPM S60V","PM stainless","58–61",3.5f,7f,7f,5f,"High wear stainless with moderate corrosion resistance."));
        s.add(s("CPM S90V","PM stainless","59–61",3.5f,9f,7.5f,3f,"One of the highest edge-retention stainless steels with relatively good toughness for its class."));
        s.add(s("CPM SPY27","PM stainless","60–62",5f,5f,8f,7f,"Knife Steel Nerds testing found behavior very similar to S35VN, with corrosion roughly in the S30V class."));
        s.add(s("CTS-204P","PM stainless","60–62",3.5f,6.5f,9f,5.5f,"M390-family steel with high corrosion resistance and strong edge retention."));
        s.add(s("CTS-BD1N","Nitrogen stainless","58–61",3.5f,3.5f,8.5f,8.5f,"Easy-maintenance stainless with modest edge retention and strong corrosion resistance."));
        s.add(s("CTS-XHP","Powder stainless","60–64",5f,5.5f,6.5f,6.5f,"Good wear/toughness balance, but corrosion resistance is relatively low for a stainless steel."));
        s.add(s("D2","Tool steel / semi-stainless","58–62",3.5f,5f,4.5f,7f,"Classic high-wear tool steel with moderate toughness and limited corrosion resistance."));
        s.add(s("Elmax","PM stainless","60–62",4f,5.5f,8f,6.5f,"Balanced premium stainless with moderate-to-good edge retention and corrosion resistance."));
        s.add(s("K390","High-alloy tool steel","62–65",5.5f,7.5f,4f,4.5f,"Excellent wear resistance with surprisingly useful toughness; non-stainless."));
        s.add(s("LC200N","Nitrogen stainless","58–60",8.5f,3f,10f,9f,"Outstanding corrosion resistance and toughness; edge retention is limited by its low carbide volume."));
        s.add(s("M390","PM stainless","60–62",3.5f,6.5f,9f,5.5f,"High corrosion resistance and edge retention, with relatively low toughness."));
        s.add(s("M398","PM stainless","60–63",2.5f,9f,8f,3f,"Very high stainless wear resistance with low toughness."));
        s.add(s("MagnaMax","PM stainless","62–65",5.5f,8.5f,9.5f,3.5f,"High edge retention combined with excellent corrosion resistance; newer high-wear sibling to MagnaCut."));
        s.add(s("Maxamet","High-speed tool steel","67–70",2f,11f,4.5f,1f,"Extreme hardness and edge retention; Knife Steel Nerds rates it above 10 for edge retention."));
        s.add(s("N690","Stainless","58–61",3.5f,4.5f,8f,7.5f,"Conventional premium stainless with strong corrosion resistance."));
        s.add(s("Nitro-V","Nitrogen stainless","59–62",7.5f,3f,7f,9f,"Tough, easy-to-sharpen stainless with modest slicing edge retention."));
        s.add(s("Rex 121","High-speed PM tool steel","66–70",1f,12f,3.5f,0f,"Knife Steel Nerds edge-retention benchmark; extreme wear resistance at the expense of toughness and sharpenability."));
        s.add(s("Super Gold 2","PM stainless","59–62",4f,5f,7.5f,7f,"Balanced Japanese PM stainless with S35VN-like overall positioning."));
        s.add(s("Vanadis 8","PM tool steel","62–65",6f,7.5f,4f,4.5f,"Excellent combination of toughness and high edge retention; non-stainless."));
        s.add(s("Vanax","Nitrogen PM stainless","59–62",5f,5.5f,10f,6.5f,"Saltwater-level corrosion resistance with moderate edge retention."));
        s.add(s("VG-10","Stainless","59–61",4f,4.5f,7.5f,7.5f,"Proven stainless with moderate toughness, edge retention and good corrosion resistance."));
        s.add(s("Z-Max","High-speed PM tool steel","65–68",3.5f,10f,4f,2f,"Ultra-high edge retention and hardness with low toughness."));
        s.add(s("ZDP-189","High-carbon stainless","64–67",2f,8f,5f,4f,"Very high hardness and wear resistance with low toughness and only moderate corrosion resistance."));

        Collections.sort(s, Comparator.comparing(a -> a.name.toLowerCase(Locale.US)));
        return s;
    }
}
