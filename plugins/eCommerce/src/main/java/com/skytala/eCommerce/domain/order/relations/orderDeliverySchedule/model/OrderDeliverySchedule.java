package com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderDeliverySchedule.mapper.OrderDeliveryScheduleMapper;

public class OrderDeliverySchedule implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private Timestamp estimatedReadyDate;
private Long cartons;
private Long skidsPallets;
private BigDecimal unitsPieces;
private BigDecimal totalCubicSize;
private String totalCubicUomId;
private BigDecimal totalWeight;
private String totalWeightUomId;
private String statusId;

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

public Timestamp getEstimatedReadyDate() {
return estimatedReadyDate;
}

public void setEstimatedReadyDate(Timestamp  estimatedReadyDate) {
this.estimatedReadyDate = estimatedReadyDate;
}

public Long getCartons() {
return cartons;
}

public void setCartons(Long  cartons) {
this.cartons = cartons;
}

public Long getSkidsPallets() {
return skidsPallets;
}

public void setSkidsPallets(Long  skidsPallets) {
this.skidsPallets = skidsPallets;
}

public BigDecimal getUnitsPieces() {
return unitsPieces;
}

public void setUnitsPieces(BigDecimal  unitsPieces) {
this.unitsPieces = unitsPieces;
}

public BigDecimal getTotalCubicSize() {
return totalCubicSize;
}

public void setTotalCubicSize(BigDecimal  totalCubicSize) {
this.totalCubicSize = totalCubicSize;
}

public String getTotalCubicUomId() {
return totalCubicUomId;
}

public void setTotalCubicUomId(String  totalCubicUomId) {
this.totalCubicUomId = totalCubicUomId;
}

public BigDecimal getTotalWeight() {
return totalWeight;
}

public void setTotalWeight(BigDecimal  totalWeight) {
this.totalWeight = totalWeight;
}

public String getTotalWeightUomId() {
return totalWeightUomId;
}

public void setTotalWeightUomId(String  totalWeightUomId) {
this.totalWeightUomId = totalWeightUomId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}


public Map<String, Object> mapAttributeField() {
return OrderDeliveryScheduleMapper.map(this);
}
}
