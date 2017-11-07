package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.mapper.X509IssuerProvisionMapper;

public class X509IssuerProvision implements Serializable{

private static final long serialVersionUID = 1L;
private String certProvisionId;
private String commonName;
private String organizationalUnit;
private String organizationName;
private String cityLocality;
private String stateProvince;
private String country;
private String serialNumber;

public String getCertProvisionId() {
return certProvisionId;
}

public void setCertProvisionId(String  certProvisionId) {
this.certProvisionId = certProvisionId;
}

public String getCommonName() {
return commonName;
}

public void setCommonName(String  commonName) {
this.commonName = commonName;
}

public String getOrganizationalUnit() {
return organizationalUnit;
}

public void setOrganizationalUnit(String  organizationalUnit) {
this.organizationalUnit = organizationalUnit;
}

public String getOrganizationName() {
return organizationName;
}

public void setOrganizationName(String  organizationName) {
this.organizationName = organizationName;
}

public String getCityLocality() {
return cityLocality;
}

public void setCityLocality(String  cityLocality) {
this.cityLocality = cityLocality;
}

public String getStateProvince() {
return stateProvince;
}

public void setStateProvince(String  stateProvince) {
this.stateProvince = stateProvince;
}

public String getCountry() {
return country;
}

public void setCountry(String  country) {
this.country = country;
}

public String getSerialNumber() {
return serialNumber;
}

public void setSerialNumber(String  serialNumber) {
this.serialNumber = serialNumber;
}


public Map<String, Object> mapAttributeField() {
return X509IssuerProvisionMapper.map(this);
}
}
