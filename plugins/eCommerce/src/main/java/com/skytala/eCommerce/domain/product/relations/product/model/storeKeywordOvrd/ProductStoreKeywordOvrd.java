package com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeKeywordOvrd.ProductStoreKeywordOvrdMapper;

public class ProductStoreKeywordOvrd implements Serializable{

private static final long serialVersionUID = 1L;
private String productStoreId;
private String keyword;
private Timestamp fromDate;
private Timestamp thruDate;
private String target;
private String targetTypeEnumId;

public String getProductStoreId() {
return productStoreId;
}

public void setProductStoreId(String  productStoreId) {
this.productStoreId = productStoreId;
}

public String getKeyword() {
return keyword;
}

public void setKeyword(String  keyword) {
this.keyword = keyword;
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

public String getTarget() {
return target;
}

public void setTarget(String  target) {
this.target = target;
}

public String getTargetTypeEnumId() {
return targetTypeEnumId;
}

public void setTargetTypeEnumId(String  targetTypeEnumId) {
this.targetTypeEnumId = targetTypeEnumId;
}


public Map<String, Object> mapAttributeField() {
return ProductStoreKeywordOvrdMapper.map(this);
}
}
