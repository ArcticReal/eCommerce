package com.skytala.eCommerce.domain.product.relations.product.model.configConfig;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configConfig.ProductConfigConfigMapper;

public class ProductConfigConfig implements Serializable{

private static final long serialVersionUID = 1L;
private String configId;
private String configItemId;
private Long sequenceNum;
private String configOptionId;
private String description;

public String getConfigId() {
return configId;
}

public void setConfigId(String  configId) {
this.configId = configId;
}

public String getConfigItemId() {
return configItemId;
}

public void setConfigItemId(String  configItemId) {
this.configItemId = configItemId;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getConfigOptionId() {
return configOptionId;
}

public void setConfigOptionId(String  configOptionId) {
this.configOptionId = configOptionId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigConfigMapper.map(this);
}
}
