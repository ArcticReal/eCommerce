package com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.mapper.SubscriptionTypeAttrMapper;

public class SubscriptionTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionTypeId;
private String attrName;
private String description;

public String getSubscriptionTypeId() {
return subscriptionTypeId;
}

public void setSubscriptionTypeId(String  subscriptionTypeId) {
this.subscriptionTypeId = subscriptionTypeId;
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
return SubscriptionTypeAttrMapper.map(this);
}
}
