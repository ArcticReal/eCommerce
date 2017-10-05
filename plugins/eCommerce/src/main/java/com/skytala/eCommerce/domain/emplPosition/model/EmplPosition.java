package com.skytala.eCommerce.domain.emplPosition.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.emplPosition.mapper.EmplPositionMapper;

public class EmplPosition implements Serializable{

private static final long serialVersionUID = 1L;
private String emplPositionId;
private String statusId;
private String partyId;
private String budgetId;
private String budgetItemSeqId;
private String emplPositionTypeId;
private Timestamp estimatedFromDate;
private Timestamp estimatedThruDate;
private Boolean salaryFlag;
private Boolean exemptFlag;
private Boolean fulltimeFlag;
private Boolean temporaryFlag;
private Timestamp actualFromDate;
private Timestamp actualThruDate;

public String getEmplPositionId() {
return emplPositionId;
}

public void setEmplPositionId(String  emplPositionId) {
this.emplPositionId = emplPositionId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getBudgetItemSeqId() {
return budgetItemSeqId;
}

public void setBudgetItemSeqId(String  budgetItemSeqId) {
this.budgetItemSeqId = budgetItemSeqId;
}

public String getEmplPositionTypeId() {
return emplPositionTypeId;
}

public void setEmplPositionTypeId(String  emplPositionTypeId) {
this.emplPositionTypeId = emplPositionTypeId;
}

public Timestamp getEstimatedFromDate() {
return estimatedFromDate;
}

public void setEstimatedFromDate(Timestamp  estimatedFromDate) {
this.estimatedFromDate = estimatedFromDate;
}

public Timestamp getEstimatedThruDate() {
return estimatedThruDate;
}

public void setEstimatedThruDate(Timestamp  estimatedThruDate) {
this.estimatedThruDate = estimatedThruDate;
}

public Boolean getSalaryFlag() {
return salaryFlag;
}

public void setSalaryFlag(Boolean  salaryFlag) {
this.salaryFlag = salaryFlag;
}

public Boolean getExemptFlag() {
return exemptFlag;
}

public void setExemptFlag(Boolean  exemptFlag) {
this.exemptFlag = exemptFlag;
}

public Boolean getFulltimeFlag() {
return fulltimeFlag;
}

public void setFulltimeFlag(Boolean  fulltimeFlag) {
this.fulltimeFlag = fulltimeFlag;
}

public Boolean getTemporaryFlag() {
return temporaryFlag;
}

public void setTemporaryFlag(Boolean  temporaryFlag) {
this.temporaryFlag = temporaryFlag;
}

public Timestamp getActualFromDate() {
return actualFromDate;
}

public void setActualFromDate(Timestamp  actualFromDate) {
this.actualFromDate = actualFromDate;
}

public Timestamp getActualThruDate() {
return actualThruDate;
}

public void setActualThruDate(Timestamp  actualThruDate) {
this.actualThruDate = actualThruDate;
}


public Map<String, Object> mapAttributeField() {
return EmplPositionMapper.map(this);
}
}
