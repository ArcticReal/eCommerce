package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.mapper.PartyTaxAuthInfoMapper;

public class PartyTaxAuthInfo implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String taxAuthGeoId;
private String taxAuthPartyId;
private Timestamp fromDate;
private Timestamp thruDate;
private String partyTaxId;
private Boolean isExempt;
private Boolean isNexus;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public String getTaxAuthPartyId() {
return taxAuthPartyId;
}

public void setTaxAuthPartyId(String  taxAuthPartyId) {
this.taxAuthPartyId = taxAuthPartyId;
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

public Boolean getIsNexus() {
return isNexus;
}

public void setIsNexus(Boolean  isNexus) {
this.isNexus = isNexus;
}


public Map<String, Object> mapAttributeField() {
return PartyTaxAuthInfoMapper.map(this);
}
}
