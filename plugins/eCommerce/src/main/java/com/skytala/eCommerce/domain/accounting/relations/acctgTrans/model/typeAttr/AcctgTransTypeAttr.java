package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.typeAttr.AcctgTransTypeAttrMapper;

public class AcctgTransTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String acctgTransTypeId;
private String attrName;
private String description;

public String getAcctgTransTypeId() {
return acctgTransTypeId;
}

public void setAcctgTransTypeId(String  acctgTransTypeId) {
this.acctgTransTypeId = acctgTransTypeId;
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
return AcctgTransTypeAttrMapper.map(this);
}
}
