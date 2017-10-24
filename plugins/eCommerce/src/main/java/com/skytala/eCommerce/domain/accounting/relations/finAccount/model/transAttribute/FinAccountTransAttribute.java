package com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transAttribute.FinAccountTransAttributeMapper;

public class FinAccountTransAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTransId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getFinAccountTransId() {
return finAccountTransId;
}

public void setFinAccountTransId(String  finAccountTransId) {
this.finAccountTransId = finAccountTransId;
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
return FinAccountTransAttributeMapper.map(this);
}
}
