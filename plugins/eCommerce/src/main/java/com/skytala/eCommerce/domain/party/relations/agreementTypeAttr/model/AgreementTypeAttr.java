package com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.mapper.AgreementTypeAttrMapper;

public class AgreementTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementTypeId;
private String attrName;
private String description;

public String getAgreementTypeId() {
return agreementTypeId;
}

public void setAgreementTypeId(String  agreementTypeId) {
this.agreementTypeId = agreementTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return AgreementTypeAttrMapper.map(this);
}
}
