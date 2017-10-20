package com.skytala.eCommerce.domain.product.relations.product.model.configOption;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOption.ProductConfigOptionMapper;

public class ProductConfigOption implements Serializable{

private static final long serialVersionUID = 1L;
private String configItemId;
private String configOptionId;
private String configOptionName;
private String description;
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

public String getConfigOptionName() {
return configOptionName;
}

public void setConfigOptionName(String  configOptionName) {
this.configOptionName = configOptionName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigOptionMapper.map(this);
}
}
