package com.skytala.eCommerce.domain.order.relations.orderItem.model.workFulfillment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.workFulfillment.WorkOrderItemFulfillmentMapper;

public class WorkOrderItemFulfillment implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getShipGroupSeqId() {
return shipGroupSeqId;
}

public void setShipGroupSeqId(String  shipGroupSeqId) {
this.shipGroupSeqId = shipGroupSeqId;
}


public Map<String, Object> mapAttributeField() {
return WorkOrderItemFulfillmentMapper.map(this);
}
}
