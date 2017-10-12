package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.mapper.FixedAssetMaintOrderMapper;

public class FixedAssetMaintOrder implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String maintHistSeqId;
private String orderId;
private String orderItemSeqId;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getMaintHistSeqId() {
return maintHistSeqId;
}

public void setMaintHistSeqId(String  maintHistSeqId) {
this.maintHistSeqId = maintHistSeqId;
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


public Map<String, Object> mapAttributeField() {
return FixedAssetMaintOrderMapper.map(this);
}
}
