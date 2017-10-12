package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.mapper.SalesOpportunityHistoryMapper;

public class SalesOpportunityHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityHistoryId;
private String salesOpportunityId;
private String description;
private String nextStep;
private BigDecimal estimatedAmount;
private BigDecimal estimatedProbability;
private String currencyUomId;
private Timestamp estimatedCloseDate;
private String opportunityStageId;
private String changeNote;
private String modifiedByUserLogin;
private Timestamp modifiedTimestamp;

public String getSalesOpportunityHistoryId() {
return salesOpportunityHistoryId;
}

public void setSalesOpportunityHistoryId(String  salesOpportunityHistoryId) {
this.salesOpportunityHistoryId = salesOpportunityHistoryId;
}

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
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

public String getChangeNote() {
return changeNote;
}

public void setChangeNote(String  changeNote) {
this.changeNote = changeNote;
}

public String getModifiedByUserLogin() {
return modifiedByUserLogin;
}

public void setModifiedByUserLogin(String  modifiedByUserLogin) {
this.modifiedByUserLogin = modifiedByUserLogin;
}

public Timestamp getModifiedTimestamp() {
return modifiedTimestamp;
}

public void setModifiedTimestamp(Timestamp  modifiedTimestamp) {
this.modifiedTimestamp = modifiedTimestamp;
}


public Map<String, Object> mapAttributeField() {
return SalesOpportunityHistoryMapper.map(this);
}
}
