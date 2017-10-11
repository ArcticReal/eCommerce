package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.mapper.CommunicationEventOrderMapper;

public class CommunicationEventOrder implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String communicationEventId;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}


public Map<String, Object> mapAttributeField() {
return CommunicationEventOrderMapper.map(this);
}
}
