package com.skytala.eCommerce.domain.party.relations.partyStatus.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyStatus.mapper.PartyStatusMapper;

public class PartyStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String statusId;
private String partyId;
private Timestamp statusDate;
private String changeByUserLoginId;

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return PartyStatusMapper.map(this);
}
}
