package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.registration;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.registration.FixedAssetRegistrationMapper;

public class FixedAssetRegistration implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp registrationDate;
private String govAgencyPartyId;
private String registrationNumber;
private String licenseNumber;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
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

public Timestamp getRegistrationDate() {
return registrationDate;
}

public void setRegistrationDate(Timestamp  registrationDate) {
this.registrationDate = registrationDate;
}

public String getGovAgencyPartyId() {
return govAgencyPartyId;
}

public void setGovAgencyPartyId(String  govAgencyPartyId) {
this.govAgencyPartyId = govAgencyPartyId;
}

public String getRegistrationNumber() {
return registrationNumber;
}

public void setRegistrationNumber(String  registrationNumber) {
this.registrationNumber = registrationNumber;
}

public String getLicenseNumber() {
return licenseNumber;
}

public void setLicenseNumber(String  licenseNumber) {
this.licenseNumber = licenseNumber;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetRegistrationMapper.map(this);
}
}
