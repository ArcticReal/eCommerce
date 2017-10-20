package com.skytala.eCommerce.domain.content.relations.survey.model.questionAppl;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionAppl.SurveyQuestionApplMapper;

public class SurveyQuestionAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyId;
private String surveyQuestionId;
private Timestamp fromDate;
private Timestamp thruDate;
private String surveyPageSeqId;
private String surveyMultiRespId;
private String surveyMultiRespColId;
private Boolean requiredField;
private Long sequenceNum;
private String externalFieldRef;
private String withSurveyQuestionId;
private String withSurveyOptionSeqId;

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getSurveyQuestionId() {
return surveyQuestionId;
}

public void setSurveyQuestionId(String  surveyQuestionId) {
this.surveyQuestionId = surveyQuestionId;
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

public String getSurveyPageSeqId() {
return surveyPageSeqId;
}

public void setSurveyPageSeqId(String  surveyPageSeqId) {
this.surveyPageSeqId = surveyPageSeqId;
}

public String getSurveyMultiRespId() {
return surveyMultiRespId;
}

public void setSurveyMultiRespId(String  surveyMultiRespId) {
this.surveyMultiRespId = surveyMultiRespId;
}

public String getSurveyMultiRespColId() {
return surveyMultiRespColId;
}

public void setSurveyMultiRespColId(String  surveyMultiRespColId) {
this.surveyMultiRespColId = surveyMultiRespColId;
}

public Boolean getRequiredField() {
return requiredField;
}

public void setRequiredField(Boolean  requiredField) {
this.requiredField = requiredField;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getExternalFieldRef() {
return externalFieldRef;
}

public void setExternalFieldRef(String  externalFieldRef) {
this.externalFieldRef = externalFieldRef;
}

public String getWithSurveyQuestionId() {
return withSurveyQuestionId;
}

public void setWithSurveyQuestionId(String  withSurveyQuestionId) {
this.withSurveyQuestionId = withSurveyQuestionId;
}

public String getWithSurveyOptionSeqId() {
return withSurveyOptionSeqId;
}

public void setWithSurveyOptionSeqId(String  withSurveyOptionSeqId) {
this.withSurveyOptionSeqId = withSurveyOptionSeqId;
}


public Map<String, Object> mapAttributeField() {
return SurveyQuestionApplMapper.map(this);
}
}
