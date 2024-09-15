package com.haha.xiuxian.blocks.lingshi;

import com.haha.xiuxian.blocks.lingshi.base.LingShiMainBlockBase;
import com.haha.xiuxian.blocks.lingshi.base.LingShiOreBase;

public class LingShiOre {

    public static LingShiMainBlockBase createMainBlock(String level) {
        return new LingShiMainBlockBase(level);
    }

    public static LingShiOreBase createOreBlock(String level, int grade) {
        return new LingShiOreBase(level, grade);
    }

    public static class Low {
        public static final LingShiMainBlockBase MainBlock = createMainBlock("low");
        public static final LingShiOreBase Ore1 = createOreBlock("low", 1);
        public static final LingShiOreBase Ore2 = createOreBlock("low", 2);
        public static final LingShiOreBase Ore3 = createOreBlock("low", 3);
        public static final LingShiOreBase Ore4 = createOreBlock("low", 4);
    }

    public static class Mid {
        public static final LingShiMainBlockBase MainBlock = createMainBlock("mid");
        public static final LingShiOreBase Ore1 = createOreBlock("mid", 1);
        public static final LingShiOreBase Ore2 = createOreBlock("mid", 2);
        public static final LingShiOreBase Ore3 = createOreBlock("mid", 3);
        public static final LingShiOreBase Ore4 = createOreBlock("mid", 4);
    }

    public static class High {
        public static final LingShiMainBlockBase MainBlock = createMainBlock("high");
        public static final LingShiOreBase Ore1 = createOreBlock("high", 1);
        public static final LingShiOreBase Ore2 = createOreBlock("high", 2);
        public static final LingShiOreBase Ore3 = createOreBlock("high", 3);
        public static final LingShiOreBase Ore4 = createOreBlock("high", 4);
    }

    public static class Extreme {
        public static final LingShiMainBlockBase MainBlock = createMainBlock("extreme");
        public static final LingShiOreBase Ore1 = createOreBlock("extreme", 1);
        public static final LingShiOreBase Ore2 = createOreBlock("extreme", 2);
        public static final LingShiOreBase Ore3 = createOreBlock("extreme", 3);
        public static final LingShiOreBase Ore4 = createOreBlock("extreme", 4);
    }
}