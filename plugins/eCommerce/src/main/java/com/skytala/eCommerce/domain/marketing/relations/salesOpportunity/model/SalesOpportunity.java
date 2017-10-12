package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.SalesOpportunityMapper;

public class SalesOpportunity implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityId;
private String opportunityName;
private String description;
private String nextStep;
private Timestamp nextStepDate;
private BigDecimal estimatedAmount;
private BigDecimal estimatedProbability;
private String currencyUomId;
private String marketingCampaignId;
private String dataSourceId;
private Timestamp estimatedCloseDate;
private String opportunityStageId;
private String typeEnumId;
private String createdByUserLogin;

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
}

public String getOpportunityName() {
return opportunityName;
}

public void setOpportunityName(String  opportunityName) {
this.opportunityName = opportunityName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getNextStep() {
return nextStep;
}

public void setNextStep(String  nextStep) {
this.nextStep = nextStep;
}

public Timestamp getNextStepDate() {
return nextStepDate;
}

public void setNextStepDate(Timestamp  nextStepDate) {
this.nextStepDate = nextStepDate;
}

public BigDecimal getEstimatedAmount() {
return estimatedAmount;
}

public void setEstimatedAmount(BigDecimal  estimatedAmount) {
this.estimatedAmount = estimatedAmount;
}

public BigDecimal getEstimatedProbability() {
return estimatedProbability;
}

public void setEstimatedProbability(BigDecimal  estimatedProbability) {
this.estimatedProbability = estimatedProbability;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getMarketingCampaignId() {
return marketingCampaignId;
}

public void setMarketingCampaignId(String  marketingCampaignId) {
this.marketingCampaignId = marketingCampaignId;
}

public String getDataSourceId() {
return dataSourceId;
}

public void setDataSourceId(String  dataSourceId) {
this.dataSourceId = dataSourceId;
}

public Timestamp getEstimatedCloseDate() {
return estimatedCloseDate;
}

public void setEstimatedCloseDate(Timestamp  estimatedCloseDate) {
this.estimatedCloseDate = estimatedCloseDate;
}

public String getOpportunityStageId() {
return opportunityStageId;
}

public void setOpportunityStageId(String  opportunityStageId) {
this.opportunityStageId = opportunityStageId;
}

public String getTypeEnumId() {
return typeEnumId;
}

public void setTypeEnumId(String  typeEnumId) {
this.typeEnumId = typeEnumId;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityMapper.map(this);
}
}
