package com.skytala.eCommerce.domain.content.relations.survey.model.questionType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionType.SurveyQuestionTypeMapper;

public class SurveyQuestionType implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyQuestionTypeId;
private String description;

public String getSurveyQuestionTypeId() {
return surveyQuestionTypeId;
}

public void setSurveyQuestionTypeId(String  surveyQuestionTypeId) {
this.surveyQuestionTypeId = surveyQuestionTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SurveyQuestionTypeMapper.map(this);
}
}
