package com.skytala.eCommerce.domain.party.relations.agreement.model.termAttribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.termAttribute.AgreementTermAttributeMapper;

public class AgreementTermAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementTermId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getAgreementTermId() {
return agreementTermId;
}

public void setAgreementTermId(String  agreementTermId) {
this.agreementTermId = agreementTermId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getAttrValue() {
return attrValue;
}

public void setAttrValue(String  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return AgreementTermAttributeMapper.map(this);
}
}
