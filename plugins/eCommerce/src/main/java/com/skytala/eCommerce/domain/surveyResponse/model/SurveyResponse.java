package com.skytala.eCommerce.domain.surveyResponse.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.surveyResponse.mapper.SurveyResponseMapper;

public class SurveyResponse implements Serializable{

private static final long serialVersionUID = 1L;
private String surveyResponseId;
private String surveyId;
private String partyId;
private Timestamp responseDate;
private Timestamp lastModifiedDate;
private String referenceId;
private String generalFeedback;
private String orderId;
private String orderItemSeqId;
private String statusId;

public String getSurveyResponseId() {
return surveyResponseId;
}

public void setSurveyResponseId(String  surveyResponseId) {
this.surveyResponseId = surveyResponseId;
}

public String getSurveyId() {
return surveyId;
}

public void setSurveyId(String  surveyId) {
this.surveyId = surveyId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getResponseDate() {
return responseDate;
}

public void setResponseDate(Timestamp  responseDate) {
this.responseDate = responseDate;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getReferenceId() {
return referenceId;
}

public void setReferenceId(String  referenceId) {
this.referenceId = referenceId;
}

public String getGeneralFeedback() {
return generalFeedback;
}

public void setGeneralFeedback(String  generalFeedback) {
this.generalFeedback = generalFeedback;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}


public Map<String, Object> mapAttributeField() {
return SurveyResponseMapper.map(this);
}
}
