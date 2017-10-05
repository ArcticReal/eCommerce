package com.skytala.eCommerce.domain.surveyMultiResp.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.surveyMultiResp.mapper.SurveyMultiRespMapper;

public class SurveyMultiResp implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyId;
private String surveyMultiRespId;
private String multiRespTitle;

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

public String getMultiRespTitle() {
return multiRespTitle;
}

public void setMultiRespTitle(String  multiRespTitle) {
this.multiRespTitle = multiRespTitle;
}


public Map<String, Object> mapAttributeField() {
return SurveyMultiRespMapper.map(this);
}
}
