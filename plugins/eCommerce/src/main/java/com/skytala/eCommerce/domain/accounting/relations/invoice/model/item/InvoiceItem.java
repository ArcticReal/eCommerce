package com.skytala.eCommerce.domain.accounting.relations.invoice.model.item;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.item.InvoiceItemMapper;

public class InvoiceItem implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceId;
private String invoiceItemSeqId;
private String invoiceItemTypeId;
private String overrideGlAccountId;
private String overrideOrgPartyId;
private String inventoryItemId;
private String productId;
private String productFeatureId;
private String parentInvoiceId;
private String parentInvoiceItemSeqId;
private String uomId;
private Boolean taxableFlag;
private BigDecimal quantity;
private BigDecimal amount;
private String description;
private String taxAuthPartyId;
private String taxAuthGeoId;
private String taxAuthorityRateSeqId;
private String salesOpportunityId;

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceItemSeqId() {
return invoiceItemSeqId;
}

public void setInvoiceItemSeqId(String  invoiceItemSeqId) {
this.invoiceItemSeqId = invoiceItemSeqId;
}

public String getInvoiceItemTypeId() {
return invoiceItemTypeId;
}

public void setInvoiceItemTypeId(String  invoiceItemTypeId) {
this.invoiceItemTypeId = invoiceItemTypeId;
}

public String getOverrideGlAccountId() {
return overrideGlAccountId;
}

public void setOverrideGlAccountId(String  overrideGlAccountId) {
this.overrideGlAccountId = overrideGlAccountId;
}

public String getOverrideOrgPartyId() {
return overrideOrgPartyId;
}

public void setOverrideOrgPartyId(String  overrideOrgPartyId) {
this.overrideOrgPartyId = overrideOrgPartyId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductFeatureId() {
return productFeatureId;
}

public void setProductFeatureId(String  productFeatureId) {
this.productFeatureId = productFeatureId;
}

public String getParentInvoiceId() {
return parentInvoiceId;
}

public void setParentInvoiceId(String  parentInvoiceId) {
this.parentInvoiceId = parentInvoiceId;
}

public String getParentInvoiceItemSeqId() {
return parentInvoiceItemSeqId;
}

public void setParentInvoiceItemSeqId(String  parentInvoiceItemSeqId) {
this.parentInvoiceItemSeqId = parentInvoiceItemSeqId;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}

public Boolean getTaxableFlag() {
return taxableFlag;
}

public void setTaxableFlag(Boolean  taxableFlag) {
this.taxableFlag = taxableFlag;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getTaxAuthPartyId() {
return taxAuthPartyId;
}

public void setTaxAuthPartyId(String  taxAuthPartyId) {
this.taxAuthPartyId = taxAuthPartyId;
}

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public String getTaxAuthorityRateSeqId() {
return taxAuthorityRateSeqId;
}

public void setTaxAuthorityRateSeqId(String  taxAuthorityRateSeqId) {
this.taxAuthorityRateSeqId = taxAuthorityRateSeqId;
}

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceItemMapper.map(this);
}
}
