package com.skytala.eCommerce.domain.party.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.mapper.PartyMapper;

public class Party implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String partyTypeId;
private String externalId;
private String preferredCurrencyUomId;
private String description;
private String statusId;
private Timestamp createdDate;
private String createdByUserLogin;
private Timestamp lastModifiedDate;
private String lastModifiedByUserLogin;
private String dataSourceId;
private Boolean isUnread;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getPartyTypeId() {
return partyTypeId;
}

public void setPartyTypeId(String  partyTypeId) {
this.partyTypeId = partyTypeId;
}

public String getExternalId() {
return externalId;
}

public void setExternalId(String  externalId) {
this.externalId = externalId;
}

public String getPreferredCurrencyUomId() {
return preferredCurrencyUomId;
}

public void setPreferredCurrencyUomId(String  preferredCurrencyUomId) {
this.preferredCurrencyUomId = preferredCurrencyUomId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}

public String getDataSourceId() {
return dataSourceId;
}

public void setDataSourceId(String  dataSourceId) {
this.dataSourceId = dataSourceId;
}

public Boolean getIsUnread() {
return isUnread;
}

public void setIsUnread(Boolean  isUnread) {
this.isUnread = isUnread;
}


public Map<String, Object> mapAttributeField() {
return PartyMapper.map(this);
}
}
