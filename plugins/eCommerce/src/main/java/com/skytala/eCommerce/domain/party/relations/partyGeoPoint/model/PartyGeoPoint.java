package com.skytala.eCommerce.domain.party.relations.partyGeoPoint.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.mapper.PartyGeoPointMapper;

public class PartyGeoPoint implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String geoPointId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGeoPointId() {
return geoPointId;
}

public void setGeoPointId(String  geoPointId) {
this.geoPointId = geoPointId;
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


public Map<String, Object> mapAttributeField() {
return PartyGeoPointMapper.map(this);
}
}
