package com.skytala.eCommerce.domain.content.relations.survey.model.trigger;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.trigger.SurveyTriggerMapper;

public class SurveyTrigger implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyId;
private String surveyApplTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getSurveyApplTypeId() {
return surveyApplTypeId;
}

public void setSurveyApplTypeId(String  surveyApplTypeId) {
this.surveyApplTypeId = surveyApplTypeId;
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
return SurveyTriggerMapper.map(this);
}
}
