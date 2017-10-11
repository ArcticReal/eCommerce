package com.skytala.eCommerce.domain.product.relations.productMeter.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productMeter.mapper.ProductMeterMapper;

public class ProductMeter implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String productMeterTypeId;
private String meterUomId;
private String meterName;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductMeterTypeId() {
return productMeterTypeId;
}

public void setProductMeterTypeId(String  productMeterTypeId) {
this.productMeterTypeId = productMeterTypeId;
}

public String getMeterUomId() {
return meterUomId;
}

public void setMeterUomId(String  meterUomId) {
this.meterUomId = meterUomId;
}

public String getMeterName() {
return meterName;
}

public void setMeterName(String  meterName) {
this.meterName = meterName;
}


public Map<String, Object> mapAttributeField() {
return ProductMeterMapper.map(this);
}
}
