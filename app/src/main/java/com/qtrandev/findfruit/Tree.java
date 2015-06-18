package com.qtrandev.findfruit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quyen on 6/13/2015.
 */
public class Tree {
    private static String PROP_ALLOWPICK = "allowpick";
    private static String PROP_FULLTYPE = "fulltype";
    private static String PROP_LAT = "lat";
    private static String PROP_LNG = "lng";
    private static String PROP_MARKER = "marker";
    private static String PROP_PUBLICLOCATION = "publiclocation";
    private static String PROP_SEASON = "season";
    private static String PROP_SOURCE = "source";
    private static String PROP_TREETYPE = "treetype";
    private static String PROP_VERIFIED = "verified";

    private String type;
    private Double lat;
    private Double lng;
    private String marker = "leaf";
    private String allowpick = "Yes";
    private String verified = "No";
    private String fulltype = "Persea americana";
    private String source = "http://findfruit.co";
    private String season = "May to November";
    private String publiclocation = "No";

    public Tree() {

    }

    public Tree(String type, Double lat, Double lng) {
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    public Map<String, Object> getTreeToWrite() {
        Map<String, Object> newTree = new HashMap<String, Object>();
        newTree.put(PROP_TREETYPE, type);
        newTree.put(PROP_LAT, lat);
        newTree.put(PROP_LNG, lng);
        newTree.put(PROP_MARKER, marker);
        newTree.put(PROP_ALLOWPICK, allowpick);
        newTree.put(PROP_VERIFIED, verified);
        newTree.put(PROP_FULLTYPE, fulltype);
        newTree.put(PROP_SOURCE, source);
        newTree.put(PROP_SEASON, season);
        newTree.put(PROP_PUBLICLOCATION, publiclocation);
        return newTree;
    }
}
