package com.skytala.eCommerce.domain.perfReview.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.perfReview.mapper.PerfReviewMapper;

public class PerfReview implements Serializable{

private static final long serialVersionUID = 1L;
private String employeePartyId;
private String employeeRoleTypeId;
private String perfReviewId;
private String managerPartyId;
private String managerRoleTypeId;
private String paymentId;
private String emplPositionId;
private Timestamp fromDate;
private Timestamp thruDate;
private String comments;

public String getEmployeePartyId() {
return employeePartyId;
}

public void setEmployeePartyId(String  employeePartyId) {
this.employeePartyId = employeePartyId;
}

public String getEmployeeRoleTypeId() {
return employeeRoleTypeId;
}

public void setEmployeeRoleTypeId(String  employeeRoleTypeId) {
this.employeeRoleTypeId = employeeRoleTypeId;
}

public String getPerfReviewId() {
return perfReviewId;
}

public void setPerfReviewId(String  perfReviewId) {
this.perfReviewId = perfReviewId;
}

public String getManagerPartyId() {
return managerPartyId;
}

public void setManagerPartyId(String  managerPartyId) {
this.managerPartyId = managerPartyId;
}

public String getManagerRoleTypeId() {
return managerRoleTypeId;
}

public void setManagerRoleTypeId(String  managerRoleTypeId) {
this.managerRoleTypeId = managerRoleTypeId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public String getEmplPositionId() {
return emplPositionId;
}

public void setEmplPositionId(String  emplPositionId) {
this.emplPositionId = emplPositionId;
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

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PerfReviewMapper.map(this);
}
}
