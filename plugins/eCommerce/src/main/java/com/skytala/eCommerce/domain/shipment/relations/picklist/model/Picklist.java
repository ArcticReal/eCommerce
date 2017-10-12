package com.skytala.eCommerce.domain.shipment.relations.picklist.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.PicklistMapper;

public class Picklist implements Serializable{

private static final long serialVersionUID = 1L;
private String picklistId;
private String description;
private String facilityId;
private String shipmentMethodTypeId;
private String statusId;
private Timestamp picklistDate;
private String createdByUserLogin;
private String lastModifiedByUserLogin;

public String getPicklistId() {
return picklistId;
}

public void setPicklistId(String  picklistId) {
this.picklistId = picklistId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getPicklistDate() {
return picklistDate;
}

public void setPicklistDate(Timestamp  picklistDate) {
this.picklistDate = picklistDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}


public Map<String, Object> mapAttributeField() {
return PicklistMapper.map(this);
}
}
