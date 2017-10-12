package com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesRuleLookup.mapper.ZipSalesRuleLookupMapper;

public class ZipSalesRuleLookup implements Serializable{

private static final long serialVersionUID = 1L;
private String stateCode;
private String city;
private String county;
private Timestamp fromDate;
private String idCode;
private String taxable;
private String shipCond;

public String getStateCode() {
return stateCode;
}

public void setStateCode(String  stateCode) {
this.stateCode = stateCode;
}

public String getCity() {
return city;
}

public void setCity(String  city) {
this.city = city;
}

public String getCounty() {
return county;
}

public void setCounty(String  county) {
this.county = county;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public String getIdCode() {
return idCode;
}

public void setIdCode(String  idCode) {
this.idCode = idCode;
}

public String getTaxable() {
return taxable;
}

public void setTaxable(String  taxable) {
this.taxable = taxable;
}

public String getShipCond() {
return shipCond;
}

public void setShipCond(String  shipCond) {
this.shipCond = shipCond;
}


public Map<String, Object> mapAttributeField() {
return ZipSalesRuleLookupMapper.map(this);
}
}
