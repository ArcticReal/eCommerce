package com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthorityAssoc.mapper.TaxAuthorityAssocMapper;

public class TaxAuthorityAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String taxAuthGeoId;
private String taxAuthPartyId;
private String toTaxAuthGeoId;
private String toTaxAuthPartyId;
private Timestamp fromDate;
private Timestamp thruDate;
private String taxAuthorityAssocTypeId;

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

public String getToTaxAuthGeoId() {
return toTaxAuthGeoId;
}

public void setToTaxAuthGeoId(String  toTaxAuthGeoId) {
this.toTaxAuthGeoId = toTaxAuthGeoId;
}

public String getToTaxAuthPartyId() {
return toTaxAuthPartyId;
}

public void setToTaxAuthPartyId(String  toTaxAuthPartyId) {
this.toTaxAuthPartyId = toTaxAuthPartyId;
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

public String getTaxAuthorityAssocTypeId() {
return taxAuthorityAssocTypeId;
}

public void setTaxAuthorityAssocTypeId(String  taxAuthorityAssocTypeId) {
this.taxAuthorityAssocTypeId = taxAuthorityAssocTypeId;
}


public Map<String, Object> mapAttributeField() {
return TaxAuthorityAssocMapper.map(this);
}
}
