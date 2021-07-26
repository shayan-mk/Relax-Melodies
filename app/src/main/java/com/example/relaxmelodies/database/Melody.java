package com.example.relaxmelodies.database;


import com.example.relaxmelodies.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Entity(tableName = "Melody")
public class Melody implements Serializable {

    private static final Map<Integer, Melody> allMelodies;
    private static int baseID;
    static {
        allMelodies = new HashMap<>();
        baseID = 0;
        initMelodies();
    }

    //@PrimaryKey(autoGenerate = true)
    private final int id;

    //@ColumnInfo(name = "name")
    private final String name;

    //@ColumnInfo(name = "fileAddress")
    private final int resourceId;

    //@ColumnInfo(name = "iconAddress")
    private final int iconResource;

    private Melody(String name, int resourceId, int iconResource) {

        this.name = name;
        this.resourceId = resourceId;
        this.iconResource = iconResource;

        baseID += 1;
        this.id = baseID;
        allMelodies.put(baseID, this);
    }

    public static List<Melody> getAllMelodies() {
        return new ArrayList<>(allMelodies.values());
    }

    public static Melody getMelodyById(int id) {
        return allMelodies.get(id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getIconResource() {
        return iconResource;
    }

    private static void initMelodies(){
        new Melody("Bathing in the sea",                    R.raw.bathing_in_the_sea, R.drawable.ic_home_black_24dp);  //1001
        new Melody("Beach wave children",                   R.raw.beach_wave_children, R.drawable.ic_home_black_24dp); //1002
        new Melody("Choir group",                           R.raw.choir, R.drawable.ic_home_black_24dp);               //1003
        new Melody("Crickets by the swamp",                 R.raw.crickets_swamp, R.drawable.ic_home_black_24dp);      //1004
        new Melody("Driving in the rain",                   R.raw.driving_rain, R.drawable.ic_home_black_24dp);        //1005
        new Melody("Flowing water singing birds",           R.raw.flowing_water_birds, R.drawable.ic_home_black_24dp); //1006
        new Melody("Birds in the forest",                   R.raw.forest_birds, R.drawable.ic_home_black_24dp);        //1007
        new Melody("Rain in the forest",                    R.raw.forest_rain, R.drawable.ic_home_black_24dp);         //1008
        new Melody("Game show",                             R.raw.game_show, R.drawable.ic_home_black_24dp);           //1009
        new Melody("Heartbeat",                             R.raw.heartbeat, R.drawable.ic_home_black_24dp);           //1010
        new Melody("Heavenly swell",                        R.raw.heavenly_swell, R.drawable.ic_home_black_24dp);      //1011
        new Melody("Heavy rain on the car glass",           R.raw.heavy_rain_car_glass, R.drawable.ic_home_black_24dp);//1012
        new Melody("Typing on the keyboard",                R.raw.keyboard, R.drawable.ic_home_black_24dp);            //1013
        new Melody("Meadow ambience",                       R.raw.meadow, R.drawable.ic_home_black_24dp);              //1014
        new Melody("Typing on the plastic keyboard",        R.raw.plastic_keyboard, R.drawable.ic_home_black_24dp);    //1015
        new Melody("Children playing in the playground",    R.raw.playground, R.drawable.ic_home_black_24dp);          //1016
        new Melody("Rain and thunder",                      R.raw.rain_thunder,  R.drawable.ic_home_black_24dp);        //1017
        new Melody("River water flow",                      R.raw.river_water_flow, R.drawable.ic_home_black_24dp);    //1018
        new Melody("Sea waves",                             R.raw.sea_waves, R.drawable.ic_home_black_24dp);           //1019
        new Melody("Swamping",                              R.raw.swamp, R.drawable.ic_home_black_24dp);               //1020
        new Melody("Swell",                                 R.raw.swell, R.drawable.ic_home_black_24dp);               //1021
        new Melody("Thunder storm",                         R.raw.thunder_storm, R.drawable.ic_home_black_24dp);       //1022
        new Melody("Toy box lullaby",                       R.raw.toy_box_lullaby, R.drawable.ic_home_black_24dp);     //1023
        new Melody("Under water",                           R.raw.underwater, R.drawable.ic_home_black_24dp);          //1024
        new Melody("Urban area ambience",                   R.raw.urban, R.drawable.ic_home_black_24dp);               //1025

    }

}
