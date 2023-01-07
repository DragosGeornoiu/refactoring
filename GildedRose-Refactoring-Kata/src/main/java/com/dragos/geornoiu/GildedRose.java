package com.dragos.geornoiu;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateItemQuality() {
        for (Item item : items) {
            if (isSulfurasItem(item)) {
                continue;
            }

            updateItemQuality(item);
            decreaseSellByDays(item);
            processExpiredItem(item);
        }
    }

    private static boolean isSulfurasItem(Item item) {
        return item.name.startsWith("Sulfuras");
    }

    private static void updateItemQuality(Item item) {
        if (isBackstagePass(item)) {
            increaseBackstagePassQuality(item);
        } else if (isAgedBrie(item)) {
            increaseItemQuality(item);
        } else if (isConjuredItem(item)) {
            decreaseItemQuality(item, 2);
        } else {
            decreaseItemQuality(item);
        }
    }

    private static void processExpiredItem(Item item) {
        if (hasItemSellByDatePass(item)) {
            if (isBackstagePass(item)) {
                item.quality = 0;
            } else if (isAgedBrie(item)) {
                increaseItemQuality(item);
            } else if (isConjuredItem(item)) {
                decreaseItemQuality(item, 2);
            } else {
                decreaseItemQuality(item);
            }
        }
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.startsWith("Backstage passes");
    }

    private static boolean isMaxQualityReached(Item item) {
        return item.quality >= 50;
    }

    private static void increaseItemQuality(Item item, int qualityIncreaseBy) {
        while (qualityIncreaseBy > 0) {
            qualityIncreaseBy--;
            increaseItemQuality(item);
        }
    }

    private static void increaseItemQuality(Item item) {
        if (!isMaxQualityReached(item)) {
            item.quality = item.quality + 1;
        }
    }

    private static void increaseBackstagePassQuality(Item item) {
        if (isBackstagePassSellByDateLessThan6Days(item)) {
            increaseItemQuality(item, 3);
        } else if (isBackstagePassSellByDateLessThan11Days(item)) {
            increaseItemQuality(item, 2);
        } else {
            increaseItemQuality(item);
        }
    }

    private static boolean isBackstagePassSellByDateLessThan11Days(Item item) {
        return item.sellIn < 11;
    }

    private static boolean isBackstagePassSellByDateLessThan6Days(Item item) {
        return item.sellIn < 6;
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.startsWith("Aged Brie");
    }

    private static boolean isConjuredItem(Item item) {
        return item.name.startsWith("Conjured");
    }

    private static void decreaseSellByDays(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private static void decreaseItemQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private static void decreaseItemQuality(Item item, int qualityDecreaseBy) {
        while (qualityDecreaseBy > 0) {
            qualityDecreaseBy--;
            decreaseItemQuality(item);
        }
    }

    private static boolean hasItemSellByDatePass(Item item) {
        return item.sellIn < 0;
    }
}