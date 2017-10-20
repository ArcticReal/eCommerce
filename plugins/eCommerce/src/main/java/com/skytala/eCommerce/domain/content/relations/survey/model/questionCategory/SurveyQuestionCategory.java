package com.skytala.eCommerce.domain.content.relations.survey.model.questionCategory;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionCategory.SurveyQuestionCategoryMapper;

public class SurveyQuestionCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyQuestionCategoryId;
private String parentCategoryId;
private String description;

public String getSurveyQuestionCategoryId() {
return surveyQuestionCategoryId;
}

public void setSurveyQuestionCategoryId(String  surveyQuestionCategoryId) {
this.surveyQuestionCategoryId = surveyQuestionCategoryId;
}

public String getParentCategoryId() {
return parentCategoryId;
}

public void setParentCategoryId(String  parentCategoryId) {
this.parentCategoryId = parentCategoryId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SurveyQuestionCategoryMapper.map(this);
}
}
