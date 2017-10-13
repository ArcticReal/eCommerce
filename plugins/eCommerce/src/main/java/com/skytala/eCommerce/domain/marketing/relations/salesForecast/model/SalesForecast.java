package com.skytala.eCommerce.domain.marketing.relations.salesForecast.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.SalesForecastMapper;

public class SalesForecast implements Serializable{

private static final long serialVersionUID = 1L;
private String salesForecastId;
private String parentSalesForecastId;
private String organizationPartyId;
private String internalPartyId;
private String customTimePeriodId;
private String currencyUomId;
private BigDecimal quotaAmount;
private BigDecimal forecastAmount;
private BigDecimal bestCaseAmount;
private BigDecimal closedAmount;
private BigDecimal percentOfQuotaForecast;
private BigDecimal percentOfQuotaClosed;
private BigDecimal pipelineAmount;
private String createdByUserLoginId;
private String modifiedByUserLoginId;

public String getSalesForecastId() {
return salesForecastId;
}

public void setSalesForecastId(String  salesForecastId) {
this.salesForecastId = salesForecastId;
}

public String getParentSalesForecastId() {
return parentSalesForecastId;
}

public void setParentSalesForecastId(String  parentSalesForecastId) {
this.parentSalesForecastId = parentSalesForecastId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getInternalPartyId() {
return internalPartyId;
}

public void setInternalPartyId(String  internalPartyId) {
this.internalPartyId = internalPartyId;
}

public String getCustomTimePeriodId() {
return customTimePeriodId;
}

public void setCustomTimePeriodId(String  customTimePeriodId) {
this.customTimePeriodId = customTimePeriodId;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public BigDecimal getQuotaAmount() {
return quotaAmount;
}

public void setQuotaAmount(BigDecimal  quotaAmount) {
this.quotaAmount = quotaAmount;
}

public BigDecimal getForecastAmount() {
return forecastAmount;
}

public void setForecastAmount(BigDecimal  forecastAmount) {
this.forecastAmount = forecastAmount;
}

public BigDecimal getBestCaseAmount() {
return bestCaseAmount;
}

public void setBestCaseAmount(BigDecimal  bestCaseAmount) {
this.bestCaseAmount = bestCaseAmount;
}

public BigDecimal getClosedAmount() {
return closedAmount;
}

public void setClosedAmount(BigDecimal  closedAmount) {
this.closedAmount = closedAmount;
}

public BigDecimal getPercentOfQuotaForecast() {
return percentOfQuotaForecast;
}

public void setPercentOfQuotaForecast(BigDecimal  percentOfQuotaForecast) {
this.percentOfQuotaForecast = percentOfQuotaForecast;
}

public BigDecimal getPercentOfQuotaClosed() {
return percentOfQuotaClosed;
}

public void setPercentOfQuotaClosed(BigDecimal  percentOfQuotaClosed) {
this.percentOfQuotaClosed = percentOfQuotaClosed;
}

public BigDecimal getPipelineAmount() {
return pipelineAmount;
}

public void setPipelineAmount(BigDecimal  pipelineAmount) {
this.pipelineAmount = pipelineAmount;
}

public String getCreatedByUserLoginId() {
return createdByUserLoginId;
}

public void setCreatedByUserLoginId(String  createdByUserLoginId) {
this.createdByUserLoginId = createdByUserLoginId;
}

public String getModifiedByUserLoginId() {
return modifiedByUserLoginId;
}

public void setModifiedByUserLoginId(String  modifiedByUserLoginId) {
this.modifiedByUserLoginId = modifiedByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return SalesForecastMapper.map(this);
}
}