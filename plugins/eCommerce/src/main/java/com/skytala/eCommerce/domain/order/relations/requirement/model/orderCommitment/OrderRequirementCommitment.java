package com.skytala.eCommerce.domain.order.relations.requirement.model.orderCommitment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.orderCommitment.OrderRequirementCommitmentMapper;

public class OrderRequirementCommitment implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String requirementId;
private BigDecimal quantity;

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

public String getRequirementId() {
return requirementId;
}

public void setRequirementId(String  requirementId) {
this.requirementId = requirementId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}


public Map<String, Object> mapAttributeField() {
return OrderRequirementCommitmentMapper.map(this);
}
}
