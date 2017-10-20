package com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.workEffort.OrderHeaderWorkEffortMapper;

public class OrderHeaderWorkEffort implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String workEffortId;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}


public Map<String, Object> mapAttributeField() {
return OrderHeaderWorkEffortMapper.map(this);
}
}
