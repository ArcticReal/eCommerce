package com.skytala.eCommerce.domain.product.relations.product.model.config;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.config.ProductConfigProductMapper;

public class ProductConfigProduct implements Serializable{

private static final long serialVersionUID = 1L;
private String configItemId;
private String configOptionId;
private String productId;
private BigDecimal quantity;
private Long sequenceNum;

public String getConfigItemId() {
return configItemId;
}

public void setConfigItemId(String  configItemId) {
this.configItemId = configItemId;
}

public String getConfigOptionId() {
return configOptionId;
}

public void setConfigOptionId(String  configOptionId) {
this.configOptionId = configOptionId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigProductMapper.map(this);
}
}
