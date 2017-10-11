package com.skytala.eCommerce.domain.party.relations.partyGroup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyGroup.mapper.PartyGroupMapper;

public class PartyGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String groupName;
private String groupNameLocal;
private String officeSiteName;
private BigDecimal annualRevenue;
private Long numEmployees;
private String tickerSymbol;
private String comments;
private String logoImageUrl;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGroupName() {
return groupName;
}

public void setGroupName(String  groupName) {
this.groupName = groupName;
}

public String getGroupNameLocal() {
return groupNameLocal;
}

public void setGroupNameLocal(String  groupNameLocal) {
this.groupNameLocal = groupNameLocal;
}

public String getOfficeSiteName() {
return officeSiteName;
}

public void setOfficeSiteName(String  officeSiteName) {
this.officeSiteName = officeSiteName;
}

public BigDecimal getAnnualRevenue() {
return annualRevenue;
}

public void setAnnualRevenue(BigDecimal  annualRevenue) {
this.annualRevenue = annualRevenue;
}

public Long getNumEmployees() {
return numEmployees;
}

public void setNumEmployees(Long  numEmployees) {
this.numEmployees = numEmployees;
}

public String getTickerSymbol() {
return tickerSymbol;
}

public void setTickerSymbol(String  tickerSymbol) {
this.tickerSymbol = tickerSymbol;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getLogoImageUrl() {
return logoImageUrl;
}

public void setLogoImageUrl(String  logoImageUrl) {
this.logoImageUrl = logoImageUrl;
}


public Map<String, Object> mapAttributeField() {
return PartyGroupMapper.map(this);
}
}
