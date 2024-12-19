package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void qualityDecreaseTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality decrease for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void sellInDecreaseTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Tomato", 10, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		
		assertEquals("Failed sellIn decrease for Tomato", 9, sellIn);
	}
	
	@Test
	public void expiredQualityDecreaseTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Milk", 0, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality double decrease for Milk", 8, quality);
	}
	
	@Test
	public void qualityDecreaseLimitTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Apple", 0, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality decrease at 0 quality for Apple", 0, quality);
	}
	
	@Test
	public void agedBrieQualityIncreaseTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality increase for Aged Brie", 11, quality);
	}
	
	@Test
	public void agedBrie0DaysQualityIncreaseTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 0, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality increase at 0 days for Aged Brie", 12, quality);
	}
	
	@Test
	public void qualityIncreaseLimitTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 50));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality increase at 50 quality for Aged Brie", 50, quality);
	}
	
	@Test
	public void backstagePassQualityTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 0));
		inn.oneDay(); // D:10 Q:1
		inn.oneDay(); // D:9  Q:3
		inn.oneDay(); // D:8  Q:5
		inn.oneDay(); // D:7  Q:7
		inn.oneDay(); // D:6  Q:9
		inn.oneDay(); // D:5  Q:11
		inn.oneDay(); // D:4  Q:14
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality increase for Backstage passes", 14, quality);
	}
	
	@Test
	public void backstagePass0DaysQualityTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality removal for Backstage passes", 0, quality);
	}
	
	@Test
	public void sulfurasQualityTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		assertEquals("Failed quality preservation for Sulfuras", 80, quality);
	}
}
