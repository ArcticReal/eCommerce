package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.mapper.CommunicationEventProductMapper;

public class CommunicationEventProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String communicationEventId;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}


public Map<String, Object> mapAttributeField() {
return CommunicationEventProductMapper.map(this);
}
}
