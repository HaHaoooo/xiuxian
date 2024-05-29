package com.haha.xiuxian.capabilities.Chunk;

import java.util.Random;

public class DataContainerImpl implements DataContainer{

    public static DataContainerImpl dataContainer = new DataContainerImpl();

    private int lingqi;
    private boolean modified;

    public DataContainerImpl(){
        this.lingqi = new Random().nextInt(255);
        this.modified = false;
    }
    @Override
    public void setLingQi(int lingqi) {
        this.lingqi = lingqi;
    }

    @Override
    public int getLingQi() {
        return lingqi;
    }

    @Override
    public void Modified(boolean modified) {
        this.modified = modified;
    }

    @Override
    public boolean modified() {
        return modified;
    }
}
