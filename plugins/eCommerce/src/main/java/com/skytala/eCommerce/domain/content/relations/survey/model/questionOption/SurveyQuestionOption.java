package com.skytala.eCommerce.domain.content.relations.survey.model.questionOption;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionOption.SurveyQuestionOptionMapper;

public class SurveyQuestionOption implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyQuestionId;
private String surveyOptionSeqId;
private String description;
private Long sequenceNum;
private BigDecimal amountBase;
private String amountBaseUomId;
private BigDecimal weightFactor;
private Long duration;
private String durationUomId;

public String getSurveyQuestionId() {
return surveyQuestionId;
}

public void setSurveyQuestionId(String  surveyQuestionId) {
this.surveyQuestionId = surveyQuestionId;
}

public String getSurveyOptionSeqId() {
return surveyOptionSeqId;
}

public void setSurveyOptionSeqId(String  surveyOptionSeqId) {
this.surveyOptionSeqId = surveyOptionSeqId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
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


public Map<String, Object> mapAttributeField() {
return SurveyQuestionOptionMapper.map(this);
}
}
