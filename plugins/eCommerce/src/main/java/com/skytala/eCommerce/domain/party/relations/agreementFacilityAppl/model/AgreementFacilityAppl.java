package com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementFacilityAppl.mapper.AgreementFacilityApplMapper;

public class AgreementFacilityAppl implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String agreementItemSeqId;
private String facilityId;

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

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}


public Map<String, Object> mapAttributeField() {
return AgreementFacilityApplMapper.map(this);
}
}
