package com.haha.xiuxian.nbt.infoblocks;

import com.haha.xiuxian.nbt.InfoBlockRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InfoBlockList implements InfoBlockBase<ArrayList<Object>> {
    private final List<InfoBlockBase<?>> list = new ArrayList<>();

    @Override
    public ArrayList<Object> getValue() {
        ArrayList<Object> values = new ArrayList<>();
        for (InfoBlockBase<?> element : list) {
            values.add(element.getValue());
        }
        return values;
    }

    @Override
    public void setValue(ArrayList<Object> value) {
        list.clear();
        for (Object obj : value) {
            Function<Object, InfoBlockBase<?>> constructor = InfoBlockRegistry.typeRegistry.get(obj.getClass());

            if (constructor != null) {
                list.add(constructor.apply(obj));
            } else {
                throw new IllegalArgumentException("Unsupported type in setValue: " + obj.getClass().getName());
            }
        }
    }

    // 添加元素
    public void add(InfoBlockBase<?> element) {
        list.add(element);
    }
}