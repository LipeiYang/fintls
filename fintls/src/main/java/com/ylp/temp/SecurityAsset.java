package com.ylp.temp;

import java.util.Date;

public class SecurityAsset 
{
	private String label;
	private String stockCode;
	private String stockName;
	private Long noOfShares;
	private Double buyPrice;
	private Double currPrice;
	private Double prevYearEndPrice;
	private Double LastYearDividend;
	private Date buyDate;
	
	public Double getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(Double currPrice) {
		this.currPrice = currPrice;
	}
	public Double getPrevYearEndPrice() {
		return prevYearEndPrice;
	}
	public void setPrevYearEndPrice(Double prevYearEndPrice) {
		this.prevYearEndPrice = prevYearEndPrice;
	}
	public Double getLastYearDividend() {
		return LastYearDividend;
	}
	public void setLastYearDividend(Double lastYearDividend) {
		LastYearDividend = lastYearDividend;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
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
}
