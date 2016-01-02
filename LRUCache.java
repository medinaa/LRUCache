/*  Aurelio Medina Jr
 *  This code is a basic example of a least recently used cache(LRU).
 *  Uses a hash-table and linked list structures.
 *  Java's own Hashtable will obviously make more room when initialCapcity is hit.
 *  This code is to show how elements are tracked and moved to make space inside a cache. 
 *  The purpose of this program is to demonstrate the functionality of (LRU) cache memory.
 *  Hope it helps :) 
 */
import java.io.*;
import java.lang.*;
import java.util.*;


public class LRUCache {
	
	//A hash table is used to represent cache, O(1) time complexity ensures good performance. 
	//Key is Integer.....Value is TickerData
	private Hashtable<Integer, TickerData> cache;
	//We also need a linked list to keep track of least recently used data.
	private LinkedList<TickerData> lru;
	//Obviously cache space is limited, so we need to define a limit.
	int cachesize;
	//Current number of elements in Cache.
	int numofElements;
	
	//Constructor
	public LRUCache(int cachesize) {
		this.cachesize = cachesize;
		this.cache = new Hashtable<Integer, TickerData>(cachesize);
		this.lru = new LinkedList<TickerData>();
		this.numofElements = 0;
	}
	
	//Function to add data.
	boolean add(int key, TickerData d) {
		
		//Check if there is room in the cache.
		if (this.numofElements < this.cachesize) {
			this.cache.put(key, d);
			this.lru.add(d);
			this.numofElements++;
		}
		//Otherwise we have to make room. Apply (LRU).
		else {
			TickerData head = this.lru.remove();
			this.cache.remove(head.id);
			this.cache.put(key, d);
			this.lru.add(d);
		}
		return true;
	}
	
	//Function to retrieve data.
	TickerData get(int key) {
		return this.cache.get(key);
	}
	
	public static void main(String[] args) {
		String[] stocks = { "AAPL", "DRYS", "MSFT", "ZBH", "HOG", "JCI", "JCP", "NKE", "AMZN", "YELP"};
		
		TickerData[] datas = new TickerData[10];	
		
		for (int i = 0; i < stocks.length; i++) {
			datas[i] = new TickerData(stocks[i], i * 100000, i);
		}
		
		LRUCache cache = new LRUCache(5);
				
		cache.add(datas[0].id, datas[0]);
		
		cache.add(datas[1].id, datas[1]);
		
		cache.add(datas[2].id, datas[2]);
		
		cache.add(datas[3].id, datas[3]);
		
		cache.add(datas[4].id, datas[4]);
		
		/*At this point the "cache" is full and the cache will apply the 
		 * least recently used rule in Cache.add() function.
		 */
		
		cache.add(datas[5].id, datas[5]);
		
		cache.add(datas[6].id, datas[6]);
		
		cache.add(datas[7].id, datas[7]);
		
		cache.add(datas[8].id, datas[8]);
		
		cache.add(datas[9].id, datas[9]);

		//Data with keys from [0-4] will no longer reside in cache memory because they have been moved via (LRU). 
		TickerData retrieved = cache.get(5);
		System.out.println(retrieved.ticker);
		System.out.println("End of Program.");
	}
}
