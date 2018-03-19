package com.example.sourabh.masterdetailflow.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        switch(position%3) {
            case 0: return "https://www.google.com";
            case 1: return "https://www.linkedin.com";
            case 2: return "https://www.github.com";
        }
        return "";
    }

/**
 * A dummy item representing a piece of content.
 */
    public static class DummyItem {
        public final String id;
        public final String item_name;
        public final String url;

        public DummyItem(String id, String item_name, String url) {
            this.id = id;
            this.item_name = item_name;
            this.url = url;
        }

        @Override
        public String toString() {
            return item_name;
        }
    }
}

