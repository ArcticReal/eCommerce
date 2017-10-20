package com.skytala.eCommerce.domain.product.relations.subscription.model.activity;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.activity.SubscriptionActivityMapper;

public class SubscriptionActivity implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionActivityId;
private String comments;
private Timestamp dateSent;

public String getSubscriptionActivityId() {
return subscriptionActivityId;
}

public void setSubscriptionActivityId(String  subscriptionActivityId) {
this.subscriptionActivityId = subscriptionActivityId;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Timestamp getDateSent() {
return dateSent;
}

public void setDateSent(Timestamp  dateSent) {
this.dateSent = dateSent;
}


public Map<String, Object> mapAttributeField() {
return SubscriptionActivityMapper.map(this);
}
}
