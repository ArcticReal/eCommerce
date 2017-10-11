package com.skytala.eCommerce.domain.party.relations.partyDataSource.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyDataSource.mapper.PartyDataSourceMapper;

public class PartyDataSource implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String dataSourceId;
private Timestamp fromDate;
private String visitId;
private String comments;
private Boolean isCreate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getDataSourceId() {
return dataSourceId;
}

public void setDataSourceId(String  dataSourceId) {
this.dataSourceId = dataSourceId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Boolean getIsCreate() {
return isCreate;
}

public void setIsCreate(Boolean  isCreate) {
this.isCreate = isCreate;
}


public Map<String, Object> mapAttributeField() {
return PartyDataSourceMapper.map(this);
}
}
