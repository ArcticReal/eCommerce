package com.skytala.eCommerce.domain.product.relations.product.model.vendor;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.vendor.VendorProductMapper;

public class VendorProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String vendorPartyId;
private String productStoreGroupId;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getVendorPartyId() {
return vendorPartyId;
}

public void setVendorPartyId(String  vendorPartyId) {
this.vendorPartyId = vendorPartyId;
}

public String getProductStoreGroupId() {
return productStoreGroupId;
}

public void setProductStoreGroupId(String  productStoreGroupId) {
this.productStoreGroupId = productStoreGroupId;
}


public Map<String, Object> mapAttributeField() {
return VendorProductMapper.map(this);
}
}
