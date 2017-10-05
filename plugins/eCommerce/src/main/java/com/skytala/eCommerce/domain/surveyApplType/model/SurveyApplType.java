package com.skytala.eCommerce.domain.surveyApplType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.surveyApplType.mapper.SurveyApplTypeMapper;

public class SurveyApplType implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyApplTypeId;
private String description;

public String getSurveyApplTypeId() {
return surveyApplTypeId;
}

public void setSurveyApplTypeId(String  surveyApplTypeId) {
this.surveyApplTypeId = surveyApplTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SurveyApplTypeMapper.map(this);
}
}
