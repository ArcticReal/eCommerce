package com.skytala.eCommerce.domain.product.relations.product.model.groupOrder;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.groupOrder.ProductGroupOrderMapper;

public class ProductGroupOrder implements Serializable{

private static final long serialVersionUID = 1L;
private String groupOrderId;
private String productId;
private Timestamp fromDate;
private Timestamp thruDate;
private String statusId;
private BigDecimal reqOrderQty;
private BigDecimal soldOrderQty;
private String jobId;

public String getGroupOrderId() {
return groupOrderId;
}

public void setGroupOrderId(String  groupOrderId) {
this.groupOrderId = groupOrderId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public BigDecimal getReqOrderQty() {
return reqOrderQty;
}

public void setReqOrderQty(BigDecimal  reqOrderQty) {
this.reqOrderQty = reqOrderQty;
}

public BigDecimal getSoldOrderQty() {
return soldOrderQty;
}

public void setSoldOrderQty(BigDecimal  soldOrderQty) {
this.soldOrderQty = soldOrderQty;
}

public String getJobId() {
return jobId;
}

public void setJobId(String  jobId) {
this.jobId = jobId;
}


public Map<String, Object> mapAttributeField() {
return ProductGroupOrderMapper.map(this);
}
}
