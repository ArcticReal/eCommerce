package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.costCalc;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.costCalc.WorkEffortCostCalcMapper;

public class WorkEffortCostCalc implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String costComponentTypeId;
private String costComponentCalcId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getCostComponentTypeId() {
return costComponentTypeId;
}

public void setCostComponentTypeId(String  costComponentTypeId) {
this.costComponentTypeId = costComponentTypeId;
}

public String getCostComponentCalcId() {
return costComponentCalcId;
}

public void setCostComponentCalcId(String  costComponentCalcId) {
this.costComponentCalcId = costComponentCalcId;
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


public Map<String, Object> mapAttributeField() {
return WorkEffortCostCalcMapper.map(this);
}
}
