package com.ylp.app;

import java.io.FileReader;
import java.io.Reader;

import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.DMinMax;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class YahHisDataCSVEngn {
	private static CellProcessor[] getProcessors() {
		return new CellProcessor[] {
				new ParseDate("yyyy-MM-dd"), //Date
				new DMinMax(0,DMinMax.MAX_DOUBLE), // Open
				new DMinMax(0,DMinMax.MAX_DOUBLE), // High
				new DMinMax(0,DMinMax.MAX_DOUBLE), // Low
				new DMinMax(0,DMinMax.MAX_DOUBLE), // Close
				new LMinMax(0,LMinMax.MAX_LONG), // Volume
				new DMinMax(0,DMinMax.MAX_DOUBLE) // Adj Close
		};
	}
	private static String[] getHeader() {
		return new String[] {
				"date",
				"open",
				"high",
				"low",
				"close",
				"volume",
				"adjClose"
		};
	}
	
	public void readWithCsvBeanReader(Reader reader, YahHisDataHdlr yahHsiMeanHdlr) throws Exception 
	{
		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE);
			beanReader.getHeader(true);
			YahHisDataVo yahHsiVo;
			while ((yahHsiVo = beanReader.read(YahHisDataVo.class, getHeader(), getProcessors())) != null) {
				yahHsiMeanHdlr.process(yahHsiVo);
			}

		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String CSV_FILENAME = "/Users/lipeiyang/my-app/table.csv";
		
		
		new YahHisDataCSVEngn().readWithCsvBeanReader(new FileReader(CSV_FILENAME), new YahHisDataHdlr(){

			@Override
			public void process(YahHisDataVo yahHsiVo) {
				
				System.out.println(String.format("customer=%s", yahHsiVo));
			}
			
		});
	}
}
