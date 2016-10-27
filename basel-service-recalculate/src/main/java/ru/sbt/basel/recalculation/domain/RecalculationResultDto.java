package ru.sbt.basel.recalculation.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RecalculationResultDto {

	private Date date;
	private String security;
	private String bidCalculateResult;
	private String askCalculateDetail;

	//@JsonFormat(pattern="yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getBidCalculateResult() {
		return bidCalculateResult;
	}

	public void setBidCalculateResult(String bidCalculateResult) {
		this.bidCalculateResult = bidCalculateResult;
	}

	public String getAskCalculateDetail() {
		return askCalculateDetail;
	}

	public void setAskCalculateDetail(String askCalculateDetail) {
		this.askCalculateDetail = askCalculateDetail;
	}

}
