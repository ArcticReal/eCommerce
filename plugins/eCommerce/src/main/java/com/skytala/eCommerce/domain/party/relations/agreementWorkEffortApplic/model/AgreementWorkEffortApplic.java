package com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementWorkEffortApplic.mapper.AgreementWorkEffortApplicMapper;

public class AgreementWorkEffortApplic implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementId;
private String agreementItemSeqId;
private String workEffortId;

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

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return AgreementWorkEffortApplicMapper.map(this);
}
}
