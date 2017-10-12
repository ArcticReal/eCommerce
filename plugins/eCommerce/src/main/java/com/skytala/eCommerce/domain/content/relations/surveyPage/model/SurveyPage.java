package com.skytala.eCommerce.domain.content.relations.surveyPage.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.surveyPage.mapper.SurveyPageMapper;

public class SurveyPage implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyId;
private String surveyPageSeqId;
private String pageName;
private Long sequenceNum;

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getSurveyPageSeqId() {
return surveyPageSeqId;
}

public void setSurveyPageSeqId(String  surveyPageSeqId) {
this.surveyPageSeqId = surveyPageSeqId;
}

public String getPageName() {
return pageName;
}

public void setPageName(String  pageName) {
this.pageName = pageName;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return SurveyPageMapper.map(this);
}
}
