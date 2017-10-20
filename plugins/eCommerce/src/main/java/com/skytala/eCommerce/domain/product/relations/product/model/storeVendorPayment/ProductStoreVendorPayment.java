package com.skytala.eCommerce.domain.product.relations.product.model.storeVendorPayment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorPayment.ProductStoreVendorPaymentMapper;

public class ProductStoreVendorPayment implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String vendorPartyId;
private String paymentMethodTypeId;
private String creditCardEnumId;

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getVendorPartyId() {
return vendorPartyId;
}

public void setVendorPartyId(String  vendorPartyId) {
this.vendorPartyId = vendorPartyId;
}

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getCreditCardEnumId() {
return creditCardEnumId;
}

public void setCreditCardEnumId(String  creditCardEnumId) {
this.creditCardEnumId = creditCardEnumId;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreVendorPaymentMapper.map(this);
}
}
