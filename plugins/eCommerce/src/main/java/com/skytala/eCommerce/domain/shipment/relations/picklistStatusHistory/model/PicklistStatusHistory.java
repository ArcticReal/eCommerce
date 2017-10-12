package com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.picklistStatusHistory.mapper.PicklistStatusHistoryMapper;

public class PicklistStatusHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String picklistId;
private Timestamp changeDate;
private String changeUserLoginId;
private String statusId;
private String statusIdTo;

public String getPicklistId() {
return picklistId;
}

public void setPicklistId(String  picklistId) {
this.picklistId = picklistId;
}

public Timestamp getChangeDate() {
return changeDate;
}

public void setChangeDate(Timestamp  changeDate) {
this.changeDate = changeDate;
}

public String getChangeUserLoginId() {
return changeUserLoginId;
}

public void setChangeUserLoginId(String  changeUserLoginId) {
this.changeUserLoginId = changeUserLoginId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getStatusIdTo() {
return statusIdTo;
}

public void setStatusIdTo(String  statusIdTo) {
this.statusIdTo = statusIdTo;
}


public Map<String, Object> mapAttributeField() {
return PicklistStatusHistoryMapper.map(this);
}
}
