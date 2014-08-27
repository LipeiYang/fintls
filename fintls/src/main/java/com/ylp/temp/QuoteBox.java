package com.ylp.temp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

public class QuoteBox 
{
	private Double price;
	private Double dividend;
	private String label;
	//http://download.finance.yahoo.com/d/quotes.csv?s=IBM,YHOO,GOOG,MSFT&f=sl1d1t1c1ohgv
    public QuoteBox(String label) {
		try{
			this.label = label;
			String url = String.format("http://finance.yahoo.com/d/quotes.csv?s=%s&f=sl1d", this.label);
			String data = Jsoup.connect(url).ignoreContentType(true).execute().body();
			String[] fields = data.split("\n");
			this.price=(Double.valueOf(fields[0])+Double.valueOf(fields[1]))/2;
			this.dividend=Double.valueOf(fields[2]);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Double getPrice() {
		return price;
	}
	
	public Double getDividend() {
		return dividend;
	}
	
	public String getLabel() {
		return label;
	}

	public static void main( String[] args ) throws IOException
    {
		Calendar cal = Calendar.getInstance();
		for(int i=0; i<7; i++)
		{
			String d = new SimpleDateFormat("dMMMyy",Locale.US).format(cal.getTime());
			String url = String.format("http://www.hsi.com.hk/HSI-Net/static/revamp/contents/en/indexes/report/vhsi/idx_%s.csv", d);
			try{
				String data = new String(Jsoup.connect(url).ignoreContentType(true).execute().bodyAsBytes(),"unicode");
				System.out.print(data.split("\n")[2]);
			}
			catch (HttpStatusException e)
			{
				
			}
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
    }
}
