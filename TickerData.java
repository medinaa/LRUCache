import java.io.*;
import java.lang.*;

public class TickerData {

	String ticker = null;
	long marketcap = 0;
	int id = 0;
	public TickerData(String ticker, long marketcap, int id) {
		this.ticker = new String(ticker);
		this.marketcap = marketcap;
		this.id = id;
	}
}
