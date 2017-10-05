package com.skytala.eCommerce.domain.marketingCampaign.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketingCampaign.mapper.MarketingCampaignMapper;

public class MarketingCampaign implements Serializable{

private static final long serialVersionUID = 1L;
private String marketingCampaignId;
private String parentCampaignId;
private String statusId;
private String campaignName;
private String campaignSummary;
private BigDecimal budgetedCost;
private BigDecimal actualCost;
private BigDecimal estimatedCost;
private String currencyUomId;
private Timestamp fromDate;
private Timestamp thruDate;
private Boolean isActive;
private String convertedLeads;
private BigDecimal expectedResponsePercent;
private BigDecimal expectedRevenue;
private Long numSent;
private Timestamp startDate;
private String createdByUserLogin;
private String lastModifiedByUserLogin;

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getParentCampaignId() {
return parentCampaignId;
}

public void setParentCampaignId(String  parentCampaignId) {
this.parentCampaignId = parentCampaignId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getCampaignName() {
return campaignName;
}

public void setCampaignName(String  campaignName) {
this.campaignName = campaignName;
}

public String getCampaignSummary() {
return campaignSummary;
}

public void setCampaignSummary(String  campaignSummary) {
this.campaignSummary = campaignSummary;
}

public BigDecimal getBudgetedCost() {
return budgetedCost;
}

public void setBudgetedCost(BigDecimal  budgetedCost) {
this.budgetedCost = budgetedCost;
}

public BigDecimal getActualCost() {
return actualCost;
}

public void setActualCost(BigDecimal  actualCost) {
this.actualCost = actualCost;
}

public BigDecimal getEstimatedCost() {
return estimatedCost;
}

public void setEstimatedCost(BigDecimal  estimatedCost) {
this.estimatedCost = estimatedCost;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Boolean getIsActive() {
return isActive;
}

public void setIsActive(Boolean  isActive) {
this.isActive = isActive;
}

public String getConvertedLeads() {
return convertedLeads;
}

public void setConvertedLeads(String  convertedLeads) {
this.convertedLeads = convertedLeads;
}

public BigDecimal getExpectedResponsePercent() {
return expectedResponsePercent;
}

public void setExpectedResponsePercent(BigDecimal  expectedResponsePercent) {
this.expectedResponsePercent = expectedResponsePercent;
}

public BigDecimal getExpectedRevenue() {
return expectedRevenue;
}

public void setExpectedRevenue(BigDecimal  expectedRevenue) {
this.expectedRevenue = expectedRevenue;
}

public Long getNumSent() {
return numSent;
}

public void setNumSent(Long  numSent) {
this.numSent = numSent;
}

public Timestamp getStartDate() {
return startDate;
}

public void setStartDate(Timestamp  startDate) {
this.startDate = startDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return MarketingCampaignMapper.map(this);
}
}
