package com.skytala.eCommerce.domain.product.relations.product.model.storeVendorShipment;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeVendorShipment.ProductStoreVendorShipmentMapper;

public class ProductStoreVendorShipment implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String vendorPartyId;
private String shipmentMethodTypeId;
private String carrierPartyId;

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

public String getShipmentMethodTypeId() {
return shipmentMethodTypeId;
}

public void setShipmentMethodTypeId(String  shipmentMethodTypeId) {
this.shipmentMethodTypeId = shipmentMethodTypeId;
}

public String getCarrierPartyId() {
return carrierPartyId;
}

public void setCarrierPartyId(String  carrierPartyId) {
this.carrierPartyId = carrierPartyId;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreVendorShipmentMapper.map(this);
}
}
