package com.ylp.temp;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ylp.app.HistoryDataBox;

public class Service {
	public void process()
	{
		List<SecurityAsset> assetLs = new LinkedList<SecurityAsset>();
		List<PortfolioRowData> assetRowLs = new LinkedList<PortfolioRowData>();
		HistoryDataBox historyDataBox = new HistoryDataBox();
		Date lastYearEndDate = new Date();
		for(SecurityAsset asset : assetLs)
		{
			PortfolioRowData ar = new PortfolioRowData();
			ar.setStockCode(asset.getStockCode());
			ar.setStockName(asset.getStockName());
			ar.setNoOfShares(asset.getNoOfShares());
			ar.setBuyPrice(asset.getBuyPrice());
			if(null==asset.getLabel()||"N/A".equals(asset.getLabel()))
			{
				ar.setCurrPrice(asset.getCurrPrice());
				ar.setLastYearPrice(asset.getPrevYearEndPrice());
				ar.setLastYearDividend(asset.getLastYearDividend());
			}
			else
			{
				QuoteBox qb = new QuoteBox(asset.getLabel());
				ar.setCurrPrice(qb.getPrice());
				ar.setLastYearDividend(qb.getDividend());
				ar.setLastYearPrice(historyDataBox.getPrevYearClosePrice(asset.getLabel()));
			}
			if(asset.getBuyDate()!=null&&asset.getBuyDate().compareTo(lastYearEndDate)>0)
			{
				ar.setLastYearPrice(asset.getBuyPrice());
			}
			ar.setYearYield(calcYield(ar.getLastYearPrice(),ar.getCurrPrice()));
			ar.setYield(calcYield(ar.getBuyPrice(), ar.getCurrPrice()));
			ar.setBuyAssetValue(calcAssetValue(ar.getBuyPrice(),ar.getNoOfShares()));
			ar.setCurrAssetValue(calcAssetValue(ar.getCurrPrice(),ar.getNoOfShares()));
			ar.setLastYearAssetValue(calcAssetValue(ar.getLastYearPrice(),ar.getNoOfShares()));
			assetRowLs.add(ar);
		}
		PortfolioRowData ttlRow = new PortfolioRowData();
		for(PortfolioRowData assetRow : assetRowLs)
		{
			if(assetRow.getBuyAssetValue()!=null)
			{
				ttlRow.setBuyAssetValue(ttlRow.getBuyAssetValue()+assetRow.getBuyAssetValue());
			}
			if(assetRow.getCurrAssetValue()!=null)
			{
				ttlRow.setCurrAssetValue(ttlRow.getCurrAssetValue()+assetRow.getCurrAssetValue());
			}
			if(assetRow.getLastYearAssetValue()!=null)
			{
				ttlRow.setLastYearAssetValue(ttlRow.getLastYearAssetValue()+assetRow.getLastYearAssetValue());
			}
			ttlRow.setYearYield(this.calcYield(ttlRow.getLastYearAssetValue(), ttlRow.getCurrAssetValue()));
			ttlRow.setYield(this.calcYield(ttlRow.getBuyAssetValue(), ttlRow.getCurrAssetValue()));
		}
	}
	
	private Double calcAssetValue(Double price, Long qty)
	{
		if(null==price||null==qty) return null;
		return price*qty;
	}
	
	private Double calcYield(Double base, Double value)
	{
		if(null==base||null==value) return null;
		return value/base-1;
	}
}
