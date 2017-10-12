package com.skytala.eCommerce.domain.content.relations.survey.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.SurveyMapper;

public class Survey implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyId;
private String surveyName;
private String description;
private String comments;
private String submitCaption;
private String responseService;
private Boolean isAnonymous;
private Boolean allowMultiple;
private Boolean allowUpdate;
private String acroFormContentId;

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getSurveyName() {
return surveyName;
}

public void setSurveyName(String  surveyName) {
this.surveyName = surveyName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getSubmitCaption() {
return submitCaption;
}

public void setSubmitCaption(String  submitCaption) {
this.submitCaption = submitCaption;
}

public String getResponseService() {
return responseService;
}

public void setResponseService(String  responseService) {
this.responseService = responseService;
}

public Boolean getIsAnonymous() {
return isAnonymous;
}

public void setIsAnonymous(Boolean  isAnonymous) {
this.isAnonymous = isAnonymous;
}

public Boolean getAllowMultiple() {
return allowMultiple;
}

public void setAllowMultiple(Boolean  allowMultiple) {
this.allowMultiple = allowMultiple;
}

public Boolean getAllowUpdate() {
return allowUpdate;
}

public void setAllowUpdate(Boolean  allowUpdate) {
this.allowUpdate = allowUpdate;
}

public String getAcroFormContentId() {
return acroFormContentId;
}

public void setAcroFormContentId(String  acroFormContentId) {
this.acroFormContentId = acroFormContentId;
}


public Map<String, Object> mapAttributeField() {
return SurveyMapper.map(this);
}
}
