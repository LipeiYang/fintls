package com.ylp.app;

import java.util.Date;

public class YahHisDataVo {

	private Date date;

	private Double open;

	private Double high;

	private Double low;

	private Double close;

	private Long volume;

	private Double adjClose;

	@Override
	public String toString() {

		return "YahHsiVo [date=" + date + ", open=" + open + ", high=" + high

		+ ", low=" + low + ", close=" + close + ", volume=" + volume

		+ ", adjClose=" + adjClose + "]";

	}

	public Date getDate() {

		return date;

	}

	public void setDate(Date date) {

		this.date = date;

	}

	public Double getOpen() {

		return open;

	}

	public void setOpen(Double open) {

		this.open = open;

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

	public Double getClose() {

		return close;

	}

	public void setClose(Double close) {

		this.close = close;

	}

	public Long getVolume() {

		return volume;

	}

	public void setVolume(Long volume) {

		this.volume = volume;

	}

	public Double getAdjClose() {

		return adjClose;

	}

	public void setAdjClose(Double adjClose) {

		this.adjClose = adjClose;

	}

}