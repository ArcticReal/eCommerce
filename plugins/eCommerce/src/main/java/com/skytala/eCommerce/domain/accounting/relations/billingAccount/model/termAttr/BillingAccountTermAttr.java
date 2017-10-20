package com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.termAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.termAttr.BillingAccountTermAttrMapper;

public class BillingAccountTermAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String billingAccountTermId;
private String attrName;
private Long attrValue;

public String getBillingAccountTermId() {
return billingAccountTermId;
}

public void setBillingAccountTermId(String  billingAccountTermId) {
this.billingAccountTermId = billingAccountTermId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public Long getAttrValue() {
return attrValue;
}

public void setAttrValue(Long  attrValue) {
this.attrValue = attrValue;
}


public Map<String, Object> mapAttributeField() {
return BillingAccountTermAttrMapper.map(this);
}
}
