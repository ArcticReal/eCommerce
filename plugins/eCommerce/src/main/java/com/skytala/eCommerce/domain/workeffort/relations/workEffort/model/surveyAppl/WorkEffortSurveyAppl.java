package com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.surveyAppl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.surveyAppl.WorkEffortSurveyApplMapper;

public class WorkEffortSurveyAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String surveyId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
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
return WorkEffortSurveyApplMapper.map(this);
}
}
