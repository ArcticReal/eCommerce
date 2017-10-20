package com.skytala.eCommerce.domain.product.relations.product.model.configStats;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configStats.ProductConfigStatsMapper;

public class ProductConfigStats implements Serializable{

private static final long serialVersionUID = 1L;
private String configId;
private String productId;
private Long numOfConfs;
private String configTypeId;

public String getConfigId() {
return configId;
}

public void setConfigId(String  configId) {
this.configId = configId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public Long getNumOfConfs() {
return numOfConfs;
}

public void setNumOfConfs(Long  numOfConfs) {
this.numOfConfs = numOfConfs;
}

public String getConfigTypeId() {
return configTypeId;
}

public void setConfigTypeId(String  configTypeId) {
this.configTypeId = configTypeId;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigStatsMapper.map(this);
}
}
