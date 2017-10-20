package com.skytala.eCommerce.domain.product.relations.subscription.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.type.SubscriptionTypeMapper;

public class SubscriptionType implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getSubscriptionTypeId() {
return subscriptionTypeId;
}

public void setSubscriptionTypeId(String  subscriptionTypeId) {
this.subscriptionTypeId = subscriptionTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return SubscriptionTypeMapper.map(this);
}
}
