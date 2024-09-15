package com.haha.xiuxian.capabilities.playerdata.storage;

public interface DataContainer {
    void setLingLi(double lingli);

    double getLingLi();

    void setLingLiMax(double linglimax);

    double getLingLiMax();

    void setMetal(double metal);

    double getMetal();

    void setMetalMax(double metalMax);

    double getMetalMax();

    void setWood(double wood);

    double getWood();

    void setWoodMax(double woodMax);

    double getWoodMax();

    void setWater(double water);

    double getWater();

    void setWaterMax(double waterMax);

    double getWaterMax();

    void setFire(double fire);

    double getFire();

    void setFireMax(double fireMax);

    double getFireMax();

    void setDirt(double dirt);

    double getDirt();

    void setDirtMax(double dirtMax);

    double getDirtMax();

    void showGui(boolean showGui);

    boolean getBooleanOfGui();

    void setLevel(String level);

    String getLevel();
}