package com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.oldPartyTaxInfo.mapper.OldPartyTaxInfoMapper;

public class OldPartyTaxInfo implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String geoId;
private Timestamp fromDate;
private Timestamp thruDate;
private String partyTaxId;
private Boolean isExempt;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
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

public String getPartyTaxId() {
return partyTaxId;
}

public void setPartyTaxId(String  partyTaxId) {
this.partyTaxId = partyTaxId;
}

public Boolean getIsExempt() {
return isExempt;
}

public void setIsExempt(Boolean  isExempt) {
this.isExempt = isExempt;
}


public Map<String, Object> mapAttributeField() {
return OldPartyTaxInfoMapper.map(this);
}
}
