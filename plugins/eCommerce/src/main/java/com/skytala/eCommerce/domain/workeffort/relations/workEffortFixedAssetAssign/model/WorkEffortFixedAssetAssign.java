package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.mapper.WorkEffortFixedAssetAssignMapper;

public class WorkEffortFixedAssetAssign implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String fixedAssetId;
private String statusId;
private Timestamp fromDate;
private Timestamp thruDate;
private String availabilityStatusId;
private BigDecimal allocatedCost;
private String comments;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
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

public String getAvailabilityStatusId() {
return availabilityStatusId;
}

public void setAvailabilityStatusId(String  availabilityStatusId) {
this.availabilityStatusId = availabilityStatusId;
}

public BigDecimal getAllocatedCost() {
return allocatedCost;
}

public void setAllocatedCost(BigDecimal  allocatedCost) {
this.allocatedCost = allocatedCost;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortFixedAssetAssignMapper.map(this);
}
}
