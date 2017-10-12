package com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.zipSalesTaxLookup.mapper.ZipSalesTaxLookupMapper;

public class ZipSalesTaxLookup implements Serializable{

private static final long serialVersionUID = 1L;
private String zipCode;
private String stateCode;
private String city;
private String county;
private Timestamp fromDate;
private String countyFips;
private Boolean countyDefault;
private Boolean generalDefault;
private Boolean insideCity;
private String geoCode;
private BigDecimal stateSalesTax;
private BigDecimal citySalesTax;
private BigDecimal cityLocalSalesTax;
private BigDecimal countySalesTax;
private BigDecimal countyLocalSalesTax;
private BigDecimal comboSalesTax;
private BigDecimal stateUseTax;
private BigDecimal cityUseTax;
private BigDecimal cityLocalUseTax;
private BigDecimal countyUseTax;
private BigDecimal countyLocalUseTax;
private BigDecimal comboUseTax;

public String getZipCode() {
return zipCode;
}

public void setZipCode(String  zipCode) {
this.zipCode = zipCode;
}

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

public String getCountyFips() {
return countyFips;
}

public void setCountyFips(String  countyFips) {
this.countyFips = countyFips;
}

public Boolean getCountyDefault() {
return countyDefault;
}

public void setCountyDefault(Boolean  countyDefault) {
this.countyDefault = countyDefault;
}

public Boolean getGeneralDefault() {
return generalDefault;
}

public void setGeneralDefault(Boolean  generalDefault) {
this.generalDefault = generalDefault;
}

public Boolean getInsideCity() {
return insideCity;
}

public void setInsideCity(Boolean  insideCity) {
this.insideCity = insideCity;
}

public String getGeoCode() {
return geoCode;
}

public void setGeoCode(String  geoCode) {
this.geoCode = geoCode;
}

public BigDecimal getStateSalesTax() {
return stateSalesTax;
}

public void setStateSalesTax(BigDecimal  stateSalesTax) {
this.stateSalesTax = stateSalesTax;
}

public BigDecimal getCitySalesTax() {
return citySalesTax;
}

public void setCitySalesTax(BigDecimal  citySalesTax) {
this.citySalesTax = citySalesTax;
}

public BigDecimal getCityLocalSalesTax() {
return cityLocalSalesTax;
}

public void setCityLocalSalesTax(BigDecimal  cityLocalSalesTax) {
this.cityLocalSalesTax = cityLocalSalesTax;
}

public BigDecimal getCountySalesTax() {
return countySalesTax;
}

public void setCountySalesTax(BigDecimal  countySalesTax) {
this.countySalesTax = countySalesTax;
}

public BigDecimal getCountyLocalSalesTax() {
return countyLocalSalesTax;
}

public void setCountyLocalSalesTax(BigDecimal  countyLocalSalesTax) {
this.countyLocalSalesTax = countyLocalSalesTax;
}

public BigDecimal getComboSalesTax() {
return comboSalesTax;
}

public void setComboSalesTax(BigDecimal  comboSalesTax) {
this.comboSalesTax = comboSalesTax;
}

public BigDecimal getStateUseTax() {
return stateUseTax;
}

public void setStateUseTax(BigDecimal  stateUseTax) {
this.stateUseTax = stateUseTax;
}

public BigDecimal getCityUseTax() {
return cityUseTax;
}

public void setCityUseTax(BigDecimal  cityUseTax) {
this.cityUseTax = cityUseTax;
}

public BigDecimal getCityLocalUseTax() {
return cityLocalUseTax;
}

public void setCityLocalUseTax(BigDecimal  cityLocalUseTax) {
this.cityLocalUseTax = cityLocalUseTax;
}

public BigDecimal getCountyUseTax() {
return countyUseTax;
}

public void setCountyUseTax(BigDecimal  countyUseTax) {
this.countyUseTax = countyUseTax;
}

public BigDecimal getCountyLocalUseTax() {
return countyLocalUseTax;
}

public void setCountyLocalUseTax(BigDecimal  countyLocalUseTax) {
this.countyLocalUseTax = countyLocalUseTax;
}

public BigDecimal getComboUseTax() {
return comboUseTax;
}

public void setComboUseTax(BigDecimal  comboUseTax) {
this.comboUseTax = comboUseTax;
}


public Map<String, Object> mapAttributeField() {
return ZipSalesTaxLookupMapper.map(this);
}
}
