package com.skytala.eCommerce.domain.product.relations.facilityContactMech.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.mapper.FacilityContactMechMapper;

public class FacilityContactMech implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityId;
private String contactMechId;
private Timestamp fromDate;
private Timestamp thruDate;
private String extension;
private String comments;

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
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

public String getExtension() {
return extension;
}

public void setExtension(String  extension) {
this.extension = extension;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return FacilityContactMechMapper.map(this);
}
}
