package com.haha.xiuxian.nbt.infoblock;

public class InfoBlockByte implements InfoBlockBase<Byte> {
    private byte value;

    public InfoBlockByte(byte value){
        this.value = value;
    }

    @Override
    public Byte toJson() {
        return value;
    }

    @Override
    public void fromJson(Byte jsonData) {
        this.value = jsonData;
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
