package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.mapper.OrderSummaryEntryMapper;

public class OrderSummaryEntry implements Serializable{

private static final long serialVersionUID = 1L;
private Timestamp entryDate;
private String productId;
private String facilityId;
private BigDecimal totalQuantity;
private BigDecimal grossSales;
private BigDecimal productCost;

public Timestamp getEntryDate() {
return entryDate;
}

public void setEntryDate(Timestamp  entryDate) {
this.entryDate = entryDate;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public BigDecimal getTotalQuantity() {
return totalQuantity;
}

public void setTotalQuantity(BigDecimal  totalQuantity) {
this.totalQuantity = totalQuantity;
}

public BigDecimal getGrossSales() {
return grossSales;
}

public void setGrossSales(BigDecimal  grossSales) {
this.grossSales = grossSales;
}

public BigDecimal getProductCost() {
return productCost;
}

public void setProductCost(BigDecimal  productCost) {
this.productCost = productCost;
}


public Map<String, Object> mapAttributeField() {
return OrderSummaryEntryMapper.map(this);
}
}
