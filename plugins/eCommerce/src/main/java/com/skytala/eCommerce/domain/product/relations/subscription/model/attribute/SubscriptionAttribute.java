package com.skytala.eCommerce.domain.product.relations.subscription.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.attribute.SubscriptionAttributeMapper;

public class SubscriptionAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getSubscriptionId() {
return subscriptionId;
}

public void setSubscriptionId(String  subscriptionId) {
this.subscriptionId = subscriptionId;
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
return SubscriptionAttributeMapper.map(this);
}
}
