package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.mapper.CustRequestCommEventMapper;

public class CustRequestCommEvent implements Serializable{

private static final long serialVersionUID = 1L;
private String custRequestId;
private String communicationEventId;

public String getCustRequestId() {
return custRequestId;
}

public void setCustRequestId(String  custRequestId) {
this.custRequestId = custRequestId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}


public Map<String, Object> mapAttributeField() {
return CustRequestCommEventMapper.map(this);
}
}
