package com.haha.xiuxian.items.gongfa;

public enum GongFaAttribute {
    EMPTY,
    METAL,
    WOOD,
    WATER,
    FIRE,
    DIRT;
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
        }
        return "";
    }
}
