package com.ylp.app;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;

public class HistoryDataBox {
	
	public static void main(String[] args) throws Exception {
		System.out.println(new HistoryDataBox().getData(new Date(), 2,"^HSIL").size());
	}
	
	public Double getPrevYearClosePrice(String label)
	{
		try{
			Calendar prevYear = Calendar.getInstance();
		    prevYear.set(prevYear.get(Calendar.YEAR)-1, 11, 31);
			List<YahHisDataVo> ls = new HistoryDataBox().getData(prevYear.getTime(), 15, label);
			return ls.get(0).getClose();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<YahHisDataVo> getData(final Date inputDate, int rangDays, String label) throws Exception
	{
		Calendar calTo = Calendar.getInstance();
		calTo.setTime(inputDate);
		Calendar calFr = Calendar.getInstance();
		calFr.setTime(inputDate);
		calFr.add(Calendar.DAY_OF_YEAR, rangDays*-1);
		String url = String.format("http://ichart.finance.yahoo.com/table.csv?s=%s&a=%d&b=%d&c=%d&d=%d&e=%d&f=%d&g=d&ignore=.csv", 
			label,
			calFr.get(Calendar.MONTH),
			calFr.get(Calendar.DAY_OF_MONTH),
			calFr.get(Calendar.YEAR),
			calTo.get(Calendar.MONTH),
			calTo.get(Calendar.DAY_OF_MONTH),
			calTo.get(Calendar.YEAR)
			);
		String data = Jsoup.connect(url).execute().body();
		BufferedReader reader = new BufferedReader(new StringReader(data));
		final List<YahHisDataVo> rltLs = new LinkedList<YahHisDataVo>();
		new YahHisDataCSVEngn().readWithCsvBeanReader(reader, new YahHisDataHdlr(){
			@Override
			public void process(YahHisDataVo yahHsiVo) {
				rltLs.add(yahHsiVo);
			}
		});
		return rltLs;
	}
}
