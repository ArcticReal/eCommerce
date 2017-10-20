package com.skytala.eCommerce.domain.product.relations.subscription.model.resource;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.resource.SubscriptionResourceMapper;

public class SubscriptionResource implements Serializable{

private static final long serialVersionUID = 1L;
private String subscriptionResourceId;
private String parentResourceId;
private String description;
private String contentId;
private String webSiteId;
private String serviceNameOnExpiry;

public String getSubscriptionResourceId() {
return subscriptionResourceId;
}

public void setSubscriptionResourceId(String  subscriptionResourceId) {
this.subscriptionResourceId = subscriptionResourceId;
}

public String getParentResourceId() {
return parentResourceId;
}

public void setParentResourceId(String  parentResourceId) {
this.parentResourceId = parentResourceId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getWebSiteId() {
return webSiteId;
}

public void setWebSiteId(String  webSiteId) {
this.webSiteId = webSiteId;
}

public String getServiceNameOnExpiry() {
return serviceNameOnExpiry;
}

public void setServiceNameOnExpiry(String  serviceNameOnExpiry) {
this.serviceNameOnExpiry = serviceNameOnExpiry;
}


public Map<String, Object> mapAttributeField() {
return SubscriptionResourceMapper.map(this);
}
}
