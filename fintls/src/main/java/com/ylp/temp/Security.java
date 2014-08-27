package com.ylp.temp;


public class Security 
{
	private String name;
	private Double high;
	private Double low;
	private Long vol;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Long getVol() {
		return vol;
	}
	public void setVol(Long vol) {
		this.vol = vol;
	}
	
	public Double getMid()
	{
		return (this.high+this.low)/2;
	}
	
}
