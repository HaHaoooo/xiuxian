package com.haha.xiuxian.nbt.infoblocks;

public class InfoBlockByte implements InfoBlockBase<Byte> {
    private byte value;

    public InfoBlockByte(byte value){
        this.value = value;
    }

    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public void setValue(Byte value) {
        this.value = value;
    }
}
