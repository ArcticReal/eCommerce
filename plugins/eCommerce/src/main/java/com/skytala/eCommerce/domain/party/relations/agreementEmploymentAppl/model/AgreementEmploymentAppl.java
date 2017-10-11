package com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementEmploymentAppl.mapper.AgreementEmploymentApplMapper;

public class AgreementEmploymentAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String agreementItemSeqId;
private String partyIdFrom;
private String partyIdTo;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private Timestamp fromDate;
private Timestamp agreementDate;
private Timestamp thruDate;

public String getAgreementId() {
return agreementId;
}

public void setAgreementId(String  agreementId) {
this.agreementId = agreementId;
}

public String getAgreementItemSeqId() {
return agreementItemSeqId;
}

public void setAgreementItemSeqId(String  agreementItemSeqId) {
this.agreementItemSeqId = agreementItemSeqId;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}

public String getRoleTypeIdFrom() {
return roleTypeIdFrom;
}

public void setRoleTypeIdFrom(String  roleTypeIdFrom) {
this.roleTypeIdFrom = roleTypeIdFrom;
}

public String getRoleTypeIdTo() {
return roleTypeIdTo;
}

public void setRoleTypeIdTo(String  roleTypeIdTo) {
this.roleTypeIdTo = roleTypeIdTo;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getAgreementDate() {
return agreementDate;
}

public void setAgreementDate(Timestamp  agreementDate) {
this.agreementDate = agreementDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return AgreementEmploymentApplMapper.map(this);
}
}
