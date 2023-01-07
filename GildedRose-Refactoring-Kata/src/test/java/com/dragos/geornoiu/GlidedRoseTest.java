package com.dragos.geornoiu;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class GlidedRoseTest {


    @Test
    public void normalItemTest_qualityDegradesByOne() {
        Item item = new Item("normalItem", 100, 100);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(99, item.quality);
    }

    @Test
    public void normalItemTest_qualityDegradesByTwo() {
        Item item = new Item("normalItem", 100, 100);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        Assert.assertEquals(98, item.quality);
    }

    @Test
    public void normalItemTest_qualityStopsAtZero() {
        Item item = new Item("normalItem", 100, 1);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        Assert.assertEquals(0, item.quality);
    }

    @Test
    public void normalItemTest_qualityDegradesByTwoPerDayAfterSellInPasses() {
        Item item = new Item("normalItem", 2, 200);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        Assert.assertEquals(194, item.quality);
    }

    @Test
    public void agedBrieItemTest_qualityIncreassesAsSellInIsCloser() {
        Item item = new Item("Aged Brie", 30, 30);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(31, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(32, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(33, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(34, item.quality);
    }

    @Test
    public void agedBrieItemTest_qualityIncreaseLimitedTo50() {
        Item item = new Item("Aged Brie", 30, 48);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(49, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
    }

    @Test
    public void agedBrieItemTest_qualityDoesNotChangeWhenOver50() {
        Item item = new Item("Aged Brie", 30, 148);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(148, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(148, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(148, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(148, item.quality);
    }

    @Test
    public void agedBrieItemTest_qualityIncreasesByTwoAsSellByDatePasses() {
        Item item = new Item("Aged Brie", 2, 40);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(41, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(42, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(44, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(46, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(48, item.quality);
    }

    @Test
    public void sulfurasItemTest_qualityDoesntChange() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 1, 40);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(40, item.quality);
        Assert.assertEquals(1, item.sellIn);
        glidedRose.updateItemQuality();
        Assert.assertEquals(40, item.quality);
        Assert.assertEquals(1, item.sellIn);
        glidedRose.updateItemQuality();
        Assert.assertEquals(40, item.quality);
        Assert.assertEquals(1, item.sellIn);
        glidedRose.updateItemQuality();
        Assert.assertEquals(40, item.quality);
        Assert.assertEquals(1, item.sellIn);
        glidedRose.updateItemQuality();
        Assert.assertEquals(40, item.quality);
        Assert.assertEquals(1, item.sellIn);
    }

    @Test
    public void backstagePassesItemTest_qualityIncreaseWhenSellByHigherThan10Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 40);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(41, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(42, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(43, item.quality);
    }

    @Test
    public void backstagePassesItemTest_qualityIncreaseLimitTo50() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 49);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
    }

    @Test
    public void backstagePassesItemTest_qualityIncreaseWhenSellByDateLessThan10Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(11, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(13, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(15, item.quality);
    }

    @Test
    public void backstagePassesItemTest_qualityIncreaseLimitTo50WhenSellByDateLessThan10Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 47);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(49, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
    }

    @Test
    public void backstagePassesItemTest_qualityIncreaseWhenSellByDateLessThan5Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(13, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(16, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(19, item.quality);
    }

    @Test
    public void backstagePassesItemTest_qualityIncreaseLimitTo50WhenSellByDateLessThan5Days() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 45);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(48, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(50, item.quality);
    }

    @Test
    public void backstagePassesItemTest_qualityDropsTo0WhenSellByDatePasses() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 2, 2);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(5, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(8, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(0, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(0, item.quality);
    }
    @Test
    public void conjuredItemTest_qualityDegradesByTwoInOneDay() {
        Item item = new Item("Conjured", 100, 100);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(98, item.quality);
    }

    @Test
    public void conjuredItemTest_qualityDegradesByFourInTwoDays() {
        Item item = new Item("Conjured", 100, 100);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(98, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(96, item.quality);
    }

    @Test
    public void conjuredItemTest_qualityDecreaseStopsAtZero() {
        Item item = new Item("Conjured", 100, 1);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        glidedRose.updateItemQuality();
        Assert.assertEquals(0, item.quality);
    }

    @Test
    public void conjuredItemTest_qualityDegradesByFourPerDayAfterSellInPasses() {
        Item item = new Item("Conjured", 2, 200);
        GildedRose glidedRose = new GildedRose(new Item[]{item});
        glidedRose.updateItemQuality();
        Assert.assertEquals(198, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(196, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(192, item.quality);
        glidedRose.updateItemQuality();
        Assert.assertEquals(188, item.quality);
    }

}
