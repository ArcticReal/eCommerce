package com.skytala.eCommerce.domain.humanres.relations.employment.model.app;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.employment.mapper.app.EmploymentAppMapper;

public class EmploymentApp implements Serializable{

private static final long serialVersionUID = 1L;
private String applicationId;
private String emplPositionId;
private String statusId;
private String employmentAppSourceTypeId;
private String applyingPartyId;
private String referredByPartyId;
private Timestamp applicationDate;
private String approverPartyId;
private String jobRequisitionId;

public String getApplicationId() {
return applicationId;
}

public void setApplicationId(String  applicationId) {
this.applicationId = applicationId;
}

public String getEmplPositionId() {
return emplPositionId;
}

public void setEmplPositionId(String  emplPositionId) {
this.emplPositionId = emplPositionId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getEmploymentAppSourceTypeId() {
return employmentAppSourceTypeId;
}

public void setEmploymentAppSourceTypeId(String  employmentAppSourceTypeId) {
this.employmentAppSourceTypeId = employmentAppSourceTypeId;
}

public String getApplyingPartyId() {
return applyingPartyId;
}

public void setApplyingPartyId(String  applyingPartyId) {
this.applyingPartyId = applyingPartyId;
}

public String getReferredByPartyId() {
return referredByPartyId;
}

public void setReferredByPartyId(String  referredByPartyId) {
this.referredByPartyId = referredByPartyId;
}

public Timestamp getApplicationDate() {
return applicationDate;
}

public void setApplicationDate(Timestamp  applicationDate) {
this.applicationDate = applicationDate;
}

public String getApproverPartyId() {
return approverPartyId;
}

public void setApproverPartyId(String  approverPartyId) {
this.approverPartyId = approverPartyId;
}

public String getJobRequisitionId() {
return jobRequisitionId;
}

public void setJobRequisitionId(String  jobRequisitionId) {
this.jobRequisitionId = jobRequisitionId;
}


public Map<String, Object> mapAttributeField() {
return EmploymentAppMapper.map(this);
}
}
