package com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.mapper.SurveyResponseAnswerMapper;

public class SurveyResponseAnswer implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyResponseId;
private String surveyQuestionId;
private String surveyMultiRespColId;
private String surveyMultiRespId;
private Boolean booleanResponse;
private BigDecimal currencyResponse;
private BigDecimal floatResponse;
private Long numericResponse;
private String textResponse;
private String surveyOptionSeqId;
private String contentId;
private Timestamp answeredDate;
private BigDecimal amountBase;
private String amountBaseUomId;
private BigDecimal weightFactor;
private Long duration;
private String durationUomId;
private Long sequenceNum;

public String getSurveyResponseId() {
return surveyResponseId;
}

public void setSurveyResponseId(String  surveyResponseId) {
this.surveyResponseId = surveyResponseId;
}

public String getSurveyQuestionId() {
return surveyQuestionId;
}

public void setSurveyQuestionId(String  surveyQuestionId) {
this.surveyQuestionId = surveyQuestionId;
}

public String getSurveyMultiRespColId() {
return surveyMultiRespColId;
}

public void setSurveyMultiRespColId(String  surveyMultiRespColId) {
this.surveyMultiRespColId = surveyMultiRespColId;
}

public String getSurveyMultiRespId() {
return surveyMultiRespId;
}

public void setSurveyMultiRespId(String  surveyMultiRespId) {
this.surveyMultiRespId = surveyMultiRespId;
}

public Boolean getBooleanResponse() {
return booleanResponse;
}

public void setBooleanResponse(Boolean  booleanResponse) {
this.booleanResponse = booleanResponse;
}

public BigDecimal getCurrencyResponse() {
return currencyResponse;
}

public void setCurrencyResponse(BigDecimal  currencyResponse) {
this.currencyResponse = currencyResponse;
}

public BigDecimal getFloatResponse() {
return floatResponse;
}

public void setFloatResponse(BigDecimal  floatResponse) {
this.floatResponse = floatResponse;
}

public Long getNumericResponse() {
return numericResponse;
}

public void setNumericResponse(Long  numericResponse) {
this.numericResponse = numericResponse;
}

public String getTextResponse() {
return textResponse;
}

public void setTextResponse(String  textResponse) {
this.textResponse = textResponse;
}

public String getSurveyOptionSeqId() {
return surveyOptionSeqId;
}

public void setSurveyOptionSeqId(String  surveyOptionSeqId) {
this.surveyOptionSeqId = surveyOptionSeqId;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public Timestamp getAnsweredDate() {
return answeredDate;
}

public void setAnsweredDate(Timestamp  answeredDate) {
this.answeredDate = answeredDate;
}

public BigDecimal getAmountBase() {
return amountBase;
}

public void setAmountBase(BigDecimal  amountBase) {
this.amountBase = amountBase;
}

public String getAmountBaseUomId() {
return amountBaseUomId;
}

public void setAmountBaseUomId(String  amountBaseUomId) {
this.amountBaseUomId = amountBaseUomId;
}

public BigDecimal getWeightFactor() {
return weightFactor;
}

public void setWeightFactor(BigDecimal  weightFactor) {
this.weightFactor = weightFactor;
}

public Long getDuration() {
return duration;
}

public void setDuration(Long  duration) {
this.duration = duration;
}

public String getDurationUomId() {
return durationUomId;
}

public void setDurationUomId(String  durationUomId) {
this.durationUomId = durationUomId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return SurveyResponseAnswerMapper.map(this);
}
}
