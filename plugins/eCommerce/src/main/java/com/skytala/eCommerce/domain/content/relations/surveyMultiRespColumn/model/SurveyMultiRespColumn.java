package com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.mapper.SurveyMultiRespColumnMapper;

public class SurveyMultiRespColumn implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyId;
private String surveyMultiRespId;
private String surveyMultiRespColId;
private String columnTitle;
private Long sequenceNum;

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
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

public String getColumnTitle() {
return columnTitle;
}

public void setColumnTitle(String  columnTitle) {
this.columnTitle = columnTitle;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return SurveyMultiRespColumnMapper.map(this);
}
}
