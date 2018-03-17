package com.example.richsoap.backgroundData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by richsoap on 18-3-15.
 */

// TODO: 18-3-16 checkCurrentItemByUUID
// TODO: 18-3-16 uncheckCurrentItemByUUID
// TODO: 18-3-16 getTitleByUUID 
// TODO: 18-3-16 getCurrentItemByUUID
// TODO: 18-3-16 getNextItemByUUID
// TODO: 18-3-16 getPreviousItemByUUID
// TODO: 18-3-16 getListByUUID
// TODO: 18-3-16 updateListByUUID
// TODO: 18-3-16 updateItemByUUID 
// TODO: 18-3-16 deleteListByUUID 
// TODO: 18-3-16 deleteItemByUUID 
// TODO: 18-3-16 getUUIDList
// TODO: 18-3-16 swapUUID
    
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
        list.add(UUID.randomUUID());
        return list;
    }

}
