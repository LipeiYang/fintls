package com.ylp.app;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jsoup.Jsoup;

import com.ylp.temp.PortfolioRowData;

public class CalStd {
	
	public static void main(String[] args) throws Exception {
		System.out.println("DATE\tLABEL\tMEAN(%)\tMEAN\tSTD(%)\tSTD");
		String interval = "1";
//		call("20120731", "31", interval, "^HSI");
//		call("20120831", "31", interval, "^HSI");
//		call("20120930", "30", interval, "^HSI");
//		call("20121031", "31", interval, "^HSI");
//		call("20121130", "30", interval, "^HSI");
//		call("20121231", "31", interval, "^HSI");
//		call("20130131", "31", interval, "^HSI");
//		call("20130228", "28", interval, "^HSI");
//		call("20130331", "31", interval, "^HSI");
//		call("20130430", "30", interval, "^HSI");
//		call("20130531", "31", interval, "^HSI");
//		call("20130630", "30", interval, "^HSI");
//		call("20120731", "31", interval, "^HSCE");
//		call("20120831", "31", interval, "^HSCE");
//		call("20120930", "30", interval, "^HSCE");
//		call("20121031", "31", interval, "^HSCE");
//		call("20121130", "30", interval, "^HSCE");
//		call("20121231", "31", interval, "^HSCE");
//		call("20130131", "31", interval, "^HSCE");
//		call("20130228", "28", interval, "^HSCE");
//		call("20130331", "31", interval, "^HSCE");
//		call("20130430", "30", interval, "^HSCE");
//		call("20130531", "31", interval, "^HSCE");
//		call("20130630", "30", interval, "^HSCE");
//		call("20140722", "31", interval, "^HSI");
//		call("20140630", "31", interval, "^HSCE");
//		call("20140722", "90", "30", "^HSI");
		call("20140630", "90", "30", "^HSCE");
//		call("20140722", "180", "30", "^HSI");
		call("20140630", "180", "30", "^HSCE");
//		call("20140722", "360", "30", "^HSI");
		call("20140630", "360", "30", "^HSCE");
//		call("20140722", "1800", "30", "^HSI");
		call("20140630", "1800", "30", "^HSCE");
	}
	
	private static void call(String ... args) throws Exception
	{
		Date inputDate = new SimpleDateFormat("yyyyMMdd").parse(args[0]);
		int rangDays = Integer.valueOf(args[1]);
		int interval = Integer.valueOf(args[2]);
		String label = args[3];
		new CalStd().process(inputDate, rangDays, interval, label);
	}
	
	
	public void process(final Date inputDate, int rangDays, final int interval, String label) throws Exception
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
		final DescriptiveStatistics stats = new DescriptiveStatistics();
		final DescriptiveStatistics statsPoint = new DescriptiveStatistics();
		
		new YahHisDataCSVEngn().readWithCsvBeanReader(reader, new YahHisDataHdlr(){
			private YahHisDataVo preDayVo = null;
//			private YahHisDataVo curDayVo = null;
			private int bkDayCnt = 0;
			
			@Override
			public void process(YahHisDataVo yahHsiVo) {
				if(yahHsiVo.getDate().compareTo(getCalDate(bkDayCnt,inputDate))<=0){
					if(null!=preDayVo)
					{
						stats.addValue(getClsYield(preDayVo, yahHsiVo));
						statsPoint.addValue(getClsPrice(preDayVo)-getClsPrice(yahHsiVo));
					}
					preDayVo = yahHsiVo;
					bkDayCnt+=interval;
				}
			}
			
			private Date getCalDate(int bkDayCnt, Date inputDate)
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(inputDate);
				cal.add(Calendar.DAY_OF_YEAR, bkDayCnt*-1);
				return cal.getTime();
			}
			
			private Double getMidYield(YahHisDataVo preMthVo, YahHisDataVo curMthVo)
			{
				System.out.println(String.format("%1$tY%1$tm%1$td-%2$tY%2$tm%2$td %3$f %4$f %5$f", curMthVo.getDate(), preMthVo.getDate(), getMidPrice(curMthVo), getMidPrice(preMthVo), (getMidPrice(preMthVo)-getMidPrice(curMthVo))/getMidPrice(curMthVo)));
				return (getMidPrice(preMthVo)-getMidPrice(curMthVo))/getMidPrice(curMthVo);
			}
			private Double getClsYield(YahHisDataVo preMthVo, YahHisDataVo curMthVo)
			{
//				System.out.println(String.format("%1$tY%1$tm%1$td-%2$tY%2$tm%2$td %3$f %4$f %5$f %6$f", curMthVo.getDate(), preMthVo.getDate(),getClsPrice(curMthVo), getClsPrice(preMthVo), (getClsPrice(preMthVo)-getClsPrice(curMthVo))/getClsPrice(curMthVo), (getClsPrice(preMthVo)-getClsPrice(curMthVo))));
				return (getClsPrice(preMthVo)-getClsPrice(curMthVo))/getClsPrice(curMthVo);
			}
			private Double getMidPrice(YahHisDataVo vo)
			{
				return (vo.getHigh()+vo.getLow())/2;
			}
			private Double getClsPrice(YahHisDataVo vo)
			{
				return vo.getClose();
			}
		});
		
		for(double d:statsPoint.getValues())
		{
			System.out.println(d);
		}
		System.out.println(String.format("%tF\t%s\t%f\t%f\t%f\t%f", inputDate, label, stats.getMean(), statsPoint.getMean(), stats.getStandardDeviation(), statsPoint.getStandardDeviation()));
	}
}
