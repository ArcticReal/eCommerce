package com.skytala.eCommerce.domain.party.relations.agreement.model.itemTypeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemTypeAttr.AgreementItemTypeAttrMapper;

public class AgreementItemTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementItemTypeId;
private String attrName;
private String description;

public String getAgreementItemTypeId() {
return agreementItemTypeId;
}

public void setAgreementItemTypeId(String  agreementItemTypeId) {
this.agreementItemTypeId = agreementItemTypeId;
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
return AgreementItemTypeAttrMapper.map(this);
}
}
