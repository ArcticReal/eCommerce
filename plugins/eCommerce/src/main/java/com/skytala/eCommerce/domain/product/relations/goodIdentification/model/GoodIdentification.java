package com.skytala.eCommerce.domain.product.relations.goodIdentification.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.mapper.GoodIdentificationMapper;

public class GoodIdentification implements Serializable{

private static final long serialVersionUID = 1L;
private String goodIdentificationTypeId;
private String productId;
private String idValue;

public String getGoodIdentificationTypeId() {
return goodIdentificationTypeId;
}

public void setGoodIdentificationTypeId(String  goodIdentificationTypeId) {
this.goodIdentificationTypeId = goodIdentificationTypeId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getIdValue() {
return idValue;
}

public void setIdValue(String  idValue) {
this.idValue = idValue;
}


public Map<String, Object> mapAttributeField() {
return GoodIdentificationMapper.map(this);
}
}
