package com.ylp.temp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

public class HkexClient 
{
	public String getData(String label) throws IOException 
	{
		String url = String.format("http://www.hkex.com.hk/eng/stat/dmstat/dayrpt/%s.htm", label);
		return Jsoup.connect(url).ignoreContentType(true).execute().body();
	}
	public static void main( String[] args ) throws IOException
    {
		new HkexClient().handle2();
    }
	
	public void handle2() throws IOException
    {
//		System.out.println(new HkexClient().getData("hhio130708"));
		SecurityParser securityParser = new SecurityParser();
		Calendar cal = Calendar.getInstance();
		int rangDays = 60;
//		Map<String, Double> preMap = new HashMap<String, Double>();
		Double preC = null;
		Double preP = null;
		Double curC = null;
		Double curP = null;
		String preDateStr = null;
		for(int i=0 ; i<rangDays; i++)
		{
			try{
				String dateStr = new SimpleDateFormat("yyMMdd").format(cal.getTime());
				for(String line : new HkexClient().getData("hhio"+dateStr).split("\r\n"))
				{
					if(securityParser.isValid(line))
					{
						Security sec = securityParser.parse(line);
						if(sec.getName().equals("JUL-13  10000 C"))
						{
							curC = sec.getMid();
						}
						if(sec.getName().equals("JUL-13  10000 P"))
						{
							curP = sec.getMid();
						}
					}
				}
				if(preC!=null&&preP!=null&&curC!=0&&curP!=0&&curC<preC&&curP<preP)
				{
					System.out.println(String.format("%s\t%f\t%f", preDateStr, preC, preP));
					System.out.println(String.format("%s\t%f\t%f", dateStr, curC, curP));
				}
				preC = curC;
				preP = curP;
				preDateStr = dateStr;
			}catch(HttpStatusException e)
			{
//				System.out.println(new SimpleDateFormat("yyMMdd").format(cal.getTime()));
			}
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
    }
	
	public void handle() throws IOException
    {
		SecurityParser securityParser = new SecurityParser();
		List<Security> buyCallLs = new LinkedList<Security>();
		List<Security> buyPutLs = new LinkedList<Security>();
		Map<String, Security> sellMap = new HashMap<String, Security>();
		for(String line : new HkexClient().getData("hhio130708").split("\r\n"))
		{
			if(securityParser.isValid(line))
			{
				Security sec = securityParser.parse(line);
				if(sec.getVol()==0||sec.getHigh()==0||sec.getLow()==0) continue;
				if(sec.getName().indexOf(" C")>0)
				{
					buyCallLs.add(sec);
				}
				else
				{
					buyPutLs.add(sec);
				}
			}
		}
		for(String line : new HkexClient().getData("hhio130716").split("\r\n"))
		{
			if(securityParser.isValid(line))
			{
				Security sec = securityParser.parse(line);
				if(sec.getVol()==0||sec.getHigh()==0||sec.getLow()==0) continue;
				sellMap.put(sec.getName(), sec);
			}
		}
		for(Security buyCall : buyCallLs)
		{
			for(Security buyPut : buyPutLs)
			{
				Security sellCall = sellMap.get(buyCall.getName());
				Security sellPut = sellMap.get(buyPut.getName());
				if(sellCall!=null&&sellPut!=null)
				{
					Double profit = sellCall.getMid()+sellPut.getMid()-buyCall.getMid()-buyPut.getMid();
					System.out.println(String.format("%s %s %f", buyCall.getName(), buyPut.getName(), profit));
				}
			}
		}
    }
	
}
