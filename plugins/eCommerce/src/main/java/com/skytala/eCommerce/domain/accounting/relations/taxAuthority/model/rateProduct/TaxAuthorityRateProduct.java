package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateProduct.TaxAuthorityRateProductMapper;

public class TaxAuthorityRateProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String taxAuthorityRateSeqId;
private String taxAuthGeoId;
private String taxAuthPartyId;
private String taxAuthorityRateTypeId;
private String productStoreId;
private String productCategoryId;
private String titleTransferEnumId;
private BigDecimal minItemPrice;
private BigDecimal minPurchase;
private Boolean taxShipping;
private BigDecimal taxPercentage;
private Boolean taxPromotions;
private Timestamp fromDate;
private Timestamp thruDate;
private String description;

public String getTaxAuthorityRateSeqId() {
return taxAuthorityRateSeqId;
}

public void setTaxAuthorityRateSeqId(String  taxAuthorityRateSeqId) {
this.taxAuthorityRateSeqId = taxAuthorityRateSeqId;
}

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public String getTaxAuthPartyId() {
return taxAuthPartyId;
}

public void setTaxAuthPartyId(String  taxAuthPartyId) {
this.taxAuthPartyId = taxAuthPartyId;
}

public String getTaxAuthorityRateTypeId() {
return taxAuthorityRateTypeId;
}

public void setTaxAuthorityRateTypeId(String  taxAuthorityRateTypeId) {
this.taxAuthorityRateTypeId = taxAuthorityRateTypeId;
}

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getTitleTransferEnumId() {
return titleTransferEnumId;
}

public void setTitleTransferEnumId(String  titleTransferEnumId) {
this.titleTransferEnumId = titleTransferEnumId;
}

public BigDecimal getMinItemPrice() {
return minItemPrice;
}

public void setMinItemPrice(BigDecimal  minItemPrice) {
this.minItemPrice = minItemPrice;
}

public BigDecimal getMinPurchase() {
return minPurchase;
}

public void setMinPurchase(BigDecimal  minPurchase) {
this.minPurchase = minPurchase;
}

public Boolean getTaxShipping() {
return taxShipping;
}

public void setTaxShipping(Boolean  taxShipping) {
this.taxShipping = taxShipping;
}

public BigDecimal getTaxPercentage() {
return taxPercentage;
}

public void setTaxPercentage(BigDecimal  taxPercentage) {
this.taxPercentage = taxPercentage;
}

public Boolean getTaxPromotions() {
return taxPromotions;
}

public void setTaxPromotions(Boolean  taxPromotions) {
this.taxPromotions = taxPromotions;
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

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TaxAuthorityRateProductMapper.map(this);
}
}
