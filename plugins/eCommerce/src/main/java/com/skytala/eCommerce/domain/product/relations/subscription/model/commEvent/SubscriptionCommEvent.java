package com.skytala.eCommerce.domain.product.relations.subscription.model.commEvent;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.commEvent.SubscriptionCommEventMapper;

public class SubscriptionCommEvent implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionId;
private String communicationEventId;

public String getSubscriptionId() {
return subscriptionId;
}

public void setSubscriptionId(String  subscriptionId) {
this.subscriptionId = subscriptionId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}


public Map<String, Object> mapAttributeField() {
return SubscriptionCommEventMapper.map(this);
}
}
