package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.mapper.CartAbandonedLineMapper;

public class CartAbandonedLine implements Serializable{

private static final long serialVersionUID = 1L;
private String visitId;
private String cartAbandonedLineSeqId;
private String productId;
private String prodCatalogId;
private BigDecimal quantity;
private Timestamp reservStart;
private BigDecimal reservLength;
private BigDecimal reservPersons;
private BigDecimal unitPrice;
private BigDecimal reserv2ndPPPerc;
private BigDecimal reservNthPPPerc;
private String configId;
private BigDecimal totalWithAdjustments;
private Boolean wasReserved;

public String getVisitId() {
return visitId;
}

public void setVisitId(String  visitId) {
this.visitId = visitId;
}

public String getCartAbandonedLineSeqId() {
return cartAbandonedLineSeqId;
}

public void setCartAbandonedLineSeqId(String  cartAbandonedLineSeqId) {
this.cartAbandonedLineSeqId = cartAbandonedLineSeqId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProdCatalogId() {
return prodCatalogId;
}

public void setProdCatalogId(String  prodCatalogId) {
this.prodCatalogId = prodCatalogId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public Timestamp getReservStart() {
return reservStart;
}

public void setReservStart(Timestamp  reservStart) {
this.reservStart = reservStart;
}

public BigDecimal getReservLength() {
return reservLength;
}

public void setReservLength(BigDecimal  reservLength) {
this.reservLength = reservLength;
}

public BigDecimal getReservPersons() {
return reservPersons;
}

public void setReservPersons(BigDecimal  reservPersons) {
this.reservPersons = reservPersons;
}

public BigDecimal getUnitPrice() {
return unitPrice;
}

public void setUnitPrice(BigDecimal  unitPrice) {
this.unitPrice = unitPrice;
}

public BigDecimal getReserv2ndPPPerc() {
return reserv2ndPPPerc;
}

public void setReserv2ndPPPerc(BigDecimal  reserv2ndPPPerc) {
this.reserv2ndPPPerc = reserv2ndPPPerc;
}

public BigDecimal getReservNthPPPerc() {
return reservNthPPPerc;
}

public void setReservNthPPPerc(BigDecimal  reservNthPPPerc) {
this.reservNthPPPerc = reservNthPPPerc;
}

public String getConfigId() {
return configId;
}

public void setConfigId(String  configId) {
this.configId = configId;
}

public BigDecimal getTotalWithAdjustments() {
return totalWithAdjustments;
}

public void setTotalWithAdjustments(BigDecimal  totalWithAdjustments) {
this.totalWithAdjustments = totalWithAdjustments;
}

public Boolean getWasReserved() {
return wasReserved;
}

public void setWasReserved(Boolean  wasReserved) {
this.wasReserved = wasReserved;
}


public Map<String, Object> mapAttributeField() {
return CartAbandonedLineMapper.map(this);
}
}
