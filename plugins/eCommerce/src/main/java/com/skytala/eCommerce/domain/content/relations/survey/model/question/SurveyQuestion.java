package com.skytala.eCommerce.domain.content.relations.survey.model.question;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.question.SurveyQuestionMapper;

public class SurveyQuestion implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyQuestionId;
private String surveyQuestionCategoryId;
private String surveyQuestionTypeId;
private String description;
private String question;
private String hint;
private String enumTypeId;
private String geoId;
private String formatString;

public String getSurveyQuestionId() {
return surveyQuestionId;
}

public void setSurveyQuestionId(String  surveyQuestionId) {
this.surveyQuestionId = surveyQuestionId;
}

public String getSurveyQuestionCategoryId() {
return surveyQuestionCategoryId;
}

public void setSurveyQuestionCategoryId(String  surveyQuestionCategoryId) {
this.surveyQuestionCategoryId = surveyQuestionCategoryId;
}

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

public String getQuestion() {
return question;
}

public void setQuestion(String  question) {
this.question = question;
}

public String getHint() {
return hint;
}

public void setHint(String  hint) {
this.hint = hint;
}

public String getEnumTypeId() {
return enumTypeId;
}

public void setEnumTypeId(String  enumTypeId) {
this.enumTypeId = enumTypeId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
}

public String getFormatString() {
return formatString;
}

public void setFormatString(String  formatString) {
this.formatString = formatString;
}


public Map<String, Object> mapAttributeField() {
return SurveyQuestionMapper.map(this);
}
}
