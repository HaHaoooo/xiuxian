package com.haha.xiuxian;

import com.haha.xiuxian.config.MainConfig;

public enum Attributes {
    EMPTY,
    METAL,
    WOOD,
    WATER,
    FIRE,
    DIRT;

    public String getEnglish(){
        switch (this){
            case EMPTY:
                return "empty";
            case METAL:
                return "metal";
            case WOOD:
                return "wood";
            case WATER:
                return "water";
            case FIRE:
                return "fire";
            case DIRT:
                return "dirt";
            default:
                return "unknown";
        }
    }

    public String getChinese(){
        switch (this){
            case EMPTY:
                return "空";
            case METAL:
                return "金";
            case WOOD:
                return "木";
            case WATER:
                return "水";
            case FIRE:
                return "火";
            case DIRT:
                return "土";
            default:
                return "未知";
        }
    }

    public boolean getConfigValue(){
        switch (this) {
            case EMPTY:
                return MainConfig.Empty;
            case METAL:
                return MainConfig.Metal;
            case WOOD:
                return MainConfig.Wood;
            case WATER:
                return MainConfig.Water;
            case FIRE:
                return MainConfig.Fire;
            case DIRT:
                return MainConfig.Dirt;
            default:
                return false;
        }
    }
}
