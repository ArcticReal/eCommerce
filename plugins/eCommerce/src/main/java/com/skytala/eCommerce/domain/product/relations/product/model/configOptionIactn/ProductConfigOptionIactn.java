package com.skytala.eCommerce.domain.product.relations.product.model.configOptionIactn;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionIactn.ProductConfigOptionIactnMapper;

public class ProductConfigOptionIactn implements Serializable{

private static final long serialVersionUID = 1L;
private String configItemId;
private String configOptionId;
private String configItemIdTo;
private String configOptionIdTo;
private Long sequenceNum;
private String configIactnTypeId;
private String description;

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

public String getConfigItemIdTo() {
return configItemIdTo;
}

public void setConfigItemIdTo(String  configItemIdTo) {
this.configItemIdTo = configItemIdTo;
}

public String getConfigOptionIdTo() {
return configOptionIdTo;
}

public void setConfigOptionIdTo(String  configOptionIdTo) {
this.configOptionIdTo = configOptionIdTo;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}

public String getConfigIactnTypeId() {
return configIactnTypeId;
}

public void setConfigIactnTypeId(String  configIactnTypeId) {
this.configIactnTypeId = configIactnTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigOptionIactnMapper.map(this);
}
}
