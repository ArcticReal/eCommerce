package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.attribute.AcctgTransAttributeMapper;

public class AcctgTransAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String acctgTransId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getAcctgTransId() {
return acctgTransId;
}

public void setAcctgTransId(String  acctgTransId) {
this.acctgTransId = acctgTransId;
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
return AcctgTransAttributeMapper.map(this);
}
}
