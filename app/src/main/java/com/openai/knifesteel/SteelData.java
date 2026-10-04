package com.openai.knifesteel;

import java.util.*;

public final class SteelData {
    private SteelData() {}

    public static List<Steel> all() {
        List<Steel> s = new ArrayList<>();
        s.add(new Steel("K390", "High-alloy tool steel", "63–66", 6.0f, 9.5f, 2.0f, 4.0f, 8.5f, "Exceptional wear resistance and edge retention; not stainless. Excellent for hard-use slicing."));
        s.add(new Steel("CPM CruWear", "PM tool steel", "61–64", 8.0f, 7.5f, 4.0f, 6.0f, 8.0f, "Excellent balance of toughness, edge retention and edge stability. A superb EDC steel if moderate corrosion resistance is acceptable."));
        s.add(new Steel("CPM MagnaCut", "PM stainless", "61–64", 8.5f, 7.5f, 9.5f, 6.5f, 8.5f, "Unusually strong combination of toughness, corrosion resistance and edge stability for a stainless steel."));
        s.add(new Steel("CPM SPY27", "PM stainless", "60–62", 7.0f, 6.5f, 8.0f, 7.5f, 7.5f, "Balanced stainless steel designed for practical EDC use, with friendly sharpening and good toughness."));
        s.add(new Steel("CPM S30V", "PM stainless", "59–61", 6.0f, 6.8f, 7.5f, 6.0f, 7.0f, "Classic premium stainless with a balanced profile and broad real-world use."));
        s.add(new Steel("CPM S45VN", "PM stainless", "59–62", 6.0f, 7.0f, 8.3f, 6.0f, 7.2f, "Refinement of S30V with improved corrosion resistance and a balanced carbide structure."));
        s.add(new Steel("CPM S110V", "PM stainless", "60–63", 3.8f, 9.4f, 9.0f, 2.5f, 5.5f, "Very high wear resistance and corrosion resistance; more demanding to sharpen and less forgiving of edge damage."));
        s.add(new Steel("Maxamet", "High-speed tool steel", "66–70", 2.8f, 10.0f, 1.5f, 1.5f, 6.0f, "Extreme wear resistance and hardness. Outstanding edge retention, but low corrosion resistance and difficult sharpening."));
        s.add(new Steel("CTS-BD1N", "Nitrogen stainless", "59–61", 7.0f, 5.5f, 8.5f, 8.0f, 7.0f, "Easy to maintain stainless steel with good toughness and corrosion resistance."));
        s.add(new Steel("CPM 20CV", "PM stainless", "59–62", 5.0f, 8.2f, 9.0f, 4.0f, 6.5f, "High wear resistance and corrosion resistance. Similar design space to M390 and 204P."));
        s.add(new Steel("M390", "PM stainless", "59–62", 5.0f, 8.2f, 9.2f, 4.0f, 6.5f, "Premium stainless emphasizing wear and corrosion resistance."));
        s.add(new Steel("CPM 3V", "PM tool steel", "58–61", 9.8f, 6.2f, 3.0f, 6.5f, 8.0f, "Extremely tough tool steel suited to impact and hard-use applications."));
        s.add(new Steel("CPM 4V", "PM tool steel", "62–64", 8.0f, 8.2f, 2.5f, 4.8f, 8.5f, "High edge stability, toughness and wear resistance for demanding cutting."));
        s.add(new Steel("14C28N", "Nitrogen stainless", "58–62", 8.5f, 5.2f, 9.0f, 9.0f, 8.5f, "Excellent toughness, corrosion resistance and ease of sharpening; takes a keen edge."));
        s.add(new Steel("LC200N", "Nitrogen stainless", "57–60", 8.0f, 5.0f, 10.0f, 8.5f, 7.5f, "Near-immunity to corrosion with good toughness; ideal around salt water."));
        s.add(new Steel("H1", "Precipitation-hardened stainless", "57–59", 8.0f, 4.3f, 10.0f, 8.5f, 7.0f, "Exceptional corrosion resistance, especially for marine use."));
        s.add(new Steel("ZDP-189", "High-carbon stainless", "64–67", 3.0f, 9.0f, 6.0f, 2.5f, 7.0f, "Very high hardness and wear resistance, but comparatively brittle and demanding to sharpen."));
        s.add(new Steel("VG-10", "Stainless", "59–61", 6.5f, 5.8f, 8.0f, 7.5f, 7.0f, "Proven all-round stainless steel with straightforward maintenance."));
        s.add(new Steel("D2", "Tool steel / semi-stainless", "59–62", 5.5f, 7.0f, 4.5f, 5.5f, 6.0f, "High wear resistance at modest cost; corrosion resistance is only moderate."));
        s.add(new Steel("154CM", "Stainless", "58–61", 6.0f, 6.2f, 7.2f, 7.0f, 7.0f, "Well-balanced stainless steel with good machinability and maintenance."));
        return s;
    }
}
