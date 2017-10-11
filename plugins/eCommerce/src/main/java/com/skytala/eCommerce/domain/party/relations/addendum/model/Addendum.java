package com.skytala.eCommerce.domain.party.relations.addendum.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.addendum.mapper.AddendumMapper;

public class Addendum implements Serializable{

private static final long serialVersionUID = 1L;
private String addendumId;
private String agreementId;
private String agreementItemSeqId;
private Timestamp addendumCreationDate;
private Timestamp addendumEffectiveDate;
private String addendumText;

public String getAddendumId() {
return addendumId;
}

public void setAddendumId(String  addendumId) {
this.addendumId = addendumId;
}

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

public Timestamp getAddendumCreationDate() {
return addendumCreationDate;
}

public void setAddendumCreationDate(Timestamp  addendumCreationDate) {
this.addendumCreationDate = addendumCreationDate;
}

public Timestamp getAddendumEffectiveDate() {
return addendumEffectiveDate;
}

public void setAddendumEffectiveDate(Timestamp  addendumEffectiveDate) {
this.addendumEffectiveDate = addendumEffectiveDate;
}

public String getAddendumText() {
return addendumText;
}

public void setAddendumText(String  addendumText) {
this.addendumText = addendumText;
}


public Map<String, Object> mapAttributeField() {
return AddendumMapper.map(this);
}
}
