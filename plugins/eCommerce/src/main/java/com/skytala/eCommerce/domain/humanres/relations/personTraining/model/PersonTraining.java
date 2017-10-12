package com.skytala.eCommerce.domain.humanres.relations.personTraining.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.personTraining.mapper.PersonTrainingMapper;

public class PersonTraining implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String trainingRequestId;
private String trainingClassTypeId;
private String workEffortId;
private Timestamp fromDate;
private Timestamp thruDate;
private String approverId;
private String approvalStatus;
private String reason;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getTrainingRequestId() {
return trainingRequestId;
}

public void setTrainingRequestId(String  trainingRequestId) {
this.trainingRequestId = trainingRequestId;
}

public String getTrainingClassTypeId() {
return trainingClassTypeId;
}

public void setTrainingClassTypeId(String  trainingClassTypeId) {
this.trainingClassTypeId = trainingClassTypeId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
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

public String getApproverId() {
return approverId;
}

public void setApproverId(String  approverId) {
this.approverId = approverId;
}

public String getApprovalStatus() {
return approvalStatus;
}

public void setApprovalStatus(String  approvalStatus) {
this.approvalStatus = approvalStatus;
}

public String getReason() {
return reason;
}

public void setReason(String  reason) {
this.reason = reason;
}


public Map<String, Object> mapAttributeField() {
return PersonTrainingMapper.map(this);
}
}
