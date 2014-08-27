package com.ylp.temp;

public class PortfolioRowData 
{
	private String stockCode;
	private String stockName;
	private Long noOfShares = 0L;
	private Double buyPrice = 0D;
	private Double lastYearPrice = 0D;
	private Double currPrice = 0D;
	private Double yield = 0D;
	private Double yearYield = 0D;
	private Double lastYearDividend = 0D;
	private Double currAssetValue = 0D;
	private Double lastYearAssetValue = 0D;
	private Double buyAssetValue = 0D;
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public Long getNoOfShares() {
		return noOfShares;
	}
	public void setNoOfShares(Long noOfShares) {
		this.noOfShares = noOfShares;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getLastYearPrice() {
		return lastYearPrice;
	}
	public void setLastYearPrice(Double lastYearPrice) {
		this.lastYearPrice = lastYearPrice;
	}
	public Double getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(Double currPrice) {
		this.currPrice = currPrice;
	}
	public Double getYield() {
		return yield;
	}
	public void setYield(Double yield) {
		this.yield = yield;
	}
	public Double getYearYield() {
		return yearYield;
	}
	public void setYearYield(Double yearYield) {
		this.yearYield = yearYield;
	}
	public Double getLastYearDividend() {
		return lastYearDividend;
	}
	public void setLastYearDividend(Double lastYearDividend) {
		this.lastYearDividend = lastYearDividend;
	}
	public Double getCurrAssetValue() {
		return currAssetValue;
	}
	public void setCurrAssetValue(Double currAssetValue) {
		this.currAssetValue = currAssetValue;
	}
	public Double getLastYearAssetValue() {
		return lastYearAssetValue;
	}
	public void setLastYearAssetValue(Double lastYearAssetValue) {
		this.lastYearAssetValue = lastYearAssetValue;
	}
	public Double getBuyAssetValue() {
		return buyAssetValue;
	}
	public void setBuyAssetValue(Double buyAssetValue) {
		this.buyAssetValue = buyAssetValue;
	}
}
