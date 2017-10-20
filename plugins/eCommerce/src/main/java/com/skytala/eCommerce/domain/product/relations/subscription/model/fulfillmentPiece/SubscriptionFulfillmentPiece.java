package com.skytala.eCommerce.domain.product.relations.subscription.model.fulfillmentPiece;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.fulfillmentPiece.SubscriptionFulfillmentPieceMapper;

public class SubscriptionFulfillmentPiece implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionActivityId;
private String subscriptionId;

public String getSubscriptionActivityId() {
return subscriptionActivityId;
}

public void setSubscriptionActivityId(String  subscriptionActivityId) {
this.subscriptionActivityId = subscriptionActivityId;
}

public String getSubscriptionId() {
return subscriptionId;
}

public void setSubscriptionId(String  subscriptionId) {
this.subscriptionId = subscriptionId;
}


public Map<String, Object> mapAttributeField() {
return SubscriptionFulfillmentPieceMapper.map(this);
}
}
