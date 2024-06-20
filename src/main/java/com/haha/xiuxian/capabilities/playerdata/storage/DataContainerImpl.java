package com.haha.xiuxian.capabilities.playerdata.storage;

public class DataContainerImpl implements DataContainer{
    private double lingli = 0;
    private double linglimax = 0;
    private double metal = 0;
    private double metalMax = 0;
    private double wood = 0;
    private double woodMax = 0;
    private double water = 0;
    private double waterMax = 0;
    private double fire = 0;
    private double fireMax = 0;
    private double dirt = 0;
    private double dirtMax = 0;
    private boolean showGui = false;
    private String level = "凡人";
    @Override
    public void setLingLi(double lingli) {
        this.lingli = lingli;
    }

    @Override
    public double getLingLi() {
        return this.lingli;
    }

    @Override
    public void setLingLiMax(double linglimax) {
        this.linglimax = linglimax;
    }

    @Override
    public double getLingLiMax() {
        return this.linglimax;
    }

    @Override
    public void setMetal(double metal) {
        this.metal = metal;
    }

    @Override
    public double getMetal() {
        return this.metal;
    }

    @Override
    public void setMetalMax(double metalMax) {
        this.metalMax = metalMax;
    }

    @Override
    public double getMetalMax() {
        return this.metalMax;
    }

    @Override
    public void setWood(double wood) {
        this.wood = wood;
    }

    @Override
    public double getWood() {
        return this.wood;
    }

    @Override
    public void setWoodMax(double woodMax) {
        this.woodMax = woodMax;
    }

    @Override
    public double getWoodMax() {
        return this.woodMax;
    }

    @Override
    public void setWater(double water) {
        this.water = water;
    }

    @Override
    public double getWater() {
        return this.water;
    }

    @Override
    public void setWaterMax(double waterMax) {
        this.waterMax = waterMax;
    }

    @Override
    public double getWaterMax() {
        return waterMax;
    }

    @Override
    public void setFire(double fire) {
        this.fire = fire;
    }

    @Override
    public double getFire() {
        return this.fire;
    }

    @Override
    public void setFireMax(double fireMax) {
        this.fireMax = fireMax;
    }

    @Override
    public double getFireMax() {
        return this.fireMax;
    }

    @Override
    public void setDirt(double dirt) {
        this.dirt = dirt;
    }

    @Override
    public double getDirt() {
        return this.dirt;
    }

    @Override
    public void setDirtMax(double dirtMax) {
        this.dirtMax = dirtMax;
    }

    @Override
    public double getDirtMax() {
        return this.dirtMax;
    }

    @Override
    public void showGui(boolean showGui) {
        this.showGui = showGui;
    }

    @Override
    public boolean getBooleanOfGui() {
        return this.showGui;
    }

    @Override
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String getLevel() {
        return this.level;
    }
}
