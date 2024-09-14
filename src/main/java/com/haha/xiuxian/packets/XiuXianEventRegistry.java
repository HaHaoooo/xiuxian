package com.haha.xiuxian.packets;

import java.util.HashMap;
import java.util.Map;

public class XiuXianEventRegistry {
    private static final Map<String, IXiuXianEventHandler> EVENT_HANDLERS = new HashMap<>();

    public static void registerEventHandler(String eventType, IXiuXianEventHandler handler) {
        EVENT_HANDLERS.put(eventType, handler);
    }

    public static IXiuXianEventHandler getEventHandler(String eventType) {
        return EVENT_HANDLERS.get(eventType);
    }
}
