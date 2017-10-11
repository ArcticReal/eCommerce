package com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.postalAddressBoundary.mapper.PostalAddressBoundaryMapper;

public class PostalAddressBoundary implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechId;
private String geoId;

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
}


public Map<String, Object> mapAttributeField() {
return PostalAddressBoundaryMapper.map(this);
}
}
