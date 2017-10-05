package com.skytala.eCommerce.domain.supplierProduct.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.supplierProduct.mapper.SupplierProductMapper;

public class SupplierProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String partyId;
private Timestamp availableFromDate;
private Timestamp availableThruDate;
private String supplierPrefOrderId;
private String supplierRatingTypeId;
private BigDecimal standardLeadTimeDays;
private BigDecimal minimumOrderQuantity;
private BigDecimal orderQtyIncrements;
private BigDecimal unitsIncluded;
private String quantityUomId;
private String agreementId;
private String agreementItemSeqId;
private BigDecimal lastPrice;
private BigDecimal shippingPrice;
private String currencyUomId;
private String supplierProductName;
private String supplierProductId;
private Boolean canDropShip;
private String comments;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getAvailableFromDate() {
return availableFromDate;
}

public void setAvailableFromDate(Timestamp  availableFromDate) {
this.availableFromDate = availableFromDate;
}

public Timestamp getAvailableThruDate() {
return availableThruDate;
}

public void setAvailableThruDate(Timestamp  availableThruDate) {
this.availableThruDate = availableThruDate;
}

public String getSupplierPrefOrderId() {
return supplierPrefOrderId;
}

public void setSupplierPrefOrderId(String  supplierPrefOrderId) {
this.supplierPrefOrderId = supplierPrefOrderId;
}

public String getSupplierRatingTypeId() {
return supplierRatingTypeId;
}

public void setSupplierRatingTypeId(String  supplierRatingTypeId) {
this.supplierRatingTypeId = supplierRatingTypeId;
}

public BigDecimal getStandardLeadTimeDays() {
return standardLeadTimeDays;
}

public void setStandardLeadTimeDays(BigDecimal  standardLeadTimeDays) {
this.standardLeadTimeDays = standardLeadTimeDays;
}

public BigDecimal getMinimumOrderQuantity() {
return minimumOrderQuantity;
}

public void setMinimumOrderQuantity(BigDecimal  minimumOrderQuantity) {
this.minimumOrderQuantity = minimumOrderQuantity;
}

public BigDecimal getOrderQtyIncrements() {
return orderQtyIncrements;
}

public void setOrderQtyIncrements(BigDecimal  orderQtyIncrements) {
this.orderQtyIncrements = orderQtyIncrements;
}

public BigDecimal getUnitsIncluded() {
return unitsIncluded;
}

public void setUnitsIncluded(BigDecimal  unitsIncluded) {
this.unitsIncluded = unitsIncluded;
}

public String getQuantityUomId() {
return quantityUomId;
}

public void setQuantityUomId(String  quantityUomId) {
this.quantityUomId = quantityUomId;
}

public String getAgreementId() {
return agreementId;
}

public void setAgreementId(String  agreementId) {
this.agreementId = agreementId;
}

public String getAgreementItemSeqId() {
return agreementItemSeqId;
}

public void setAgreementItemSeqId(String  agreementItemSeqId) {
this.agreementItemSeqId = agreementItemSeqId;
}

public BigDecimal getLastPrice() {
return lastPrice;
}

public void setLastPrice(BigDecimal  lastPrice) {
this.lastPrice = lastPrice;
}

public BigDecimal getShippingPrice() {
return shippingPrice;
}

public void setShippingPrice(BigDecimal  shippingPrice) {
this.shippingPrice = shippingPrice;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getSupplierProductName() {
return supplierProductName;
}

public void setSupplierProductName(String  supplierProductName) {
this.supplierProductName = supplierProductName;
}

public String getSupplierProductId() {
return supplierProductId;
}

public void setSupplierProductId(String  supplierProductId) {
this.supplierProductId = supplierProductId;
}

public Boolean getCanDropShip() {
return canDropShip;
}

public void setCanDropShip(Boolean  canDropShip) {
this.canDropShip = canDropShip;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return SupplierProductMapper.map(this);
}
}
