package com.skytala.eCommerce.domain.humanres.relations.partyQual.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.PartyQualMapper;

public class PartyQual implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String partyQualTypeId;
private String qualificationDesc;
private String title;
private String statusId;
private String verifStatusId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getPartyQualTypeId() {
return partyQualTypeId;
}

public void setPartyQualTypeId(String  partyQualTypeId) {
this.partyQualTypeId = partyQualTypeId;
}

public String getQualificationDesc() {
return qualificationDesc;
}

public void setQualificationDesc(String  qualificationDesc) {
this.qualificationDesc = qualificationDesc;
}

public String getTitle() {
return title;
}

public void setTitle(String  title) {
this.title = title;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getVerifStatusId() {
return verifStatusId;
}

public void setVerifStatusId(String  verifStatusId) {
this.verifStatusId = verifStatusId;
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
return PartyQualMapper.map(this);
}
}
