package com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementGeographicalApplic.mapper.AgreementGeographicalApplicMapper;

public class AgreementGeographicalApplic implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String agreementItemSeqId;
private String geoId;

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

public String getGeoId() {
return geoId;
}

public void setGeoId(String  geoId) {
this.geoId = geoId;
}


public Map<String, Object> mapAttributeField() {
return AgreementGeographicalApplicMapper.map(this);
}
}
