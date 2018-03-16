package com.example.richsoap.backgroundData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by richsoap on 18-3-15.
 */

public class DataManager {
    public static String getMainTitleByUUID(UUID uuid) {
        return "test";
    }
    public static String getSubTitleByUUID(UUID uuid) {
        return "long long long title";
    }
    public static List<UUID> getUUIDList() {
        List<UUID> list = new ArrayList<>();
        list.add(UUID.randomUUID());
        list.add(UUID.randomUUID());
        list.add(UUID.randomUUID());
        return list;
    }
}
