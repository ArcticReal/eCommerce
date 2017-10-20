package com.skytala.eCommerce.domain.product.relations.product.model.config;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.config.ProductConfigMapper;

public class ProductConfig implements Serializable{

private static final long serialVersionUID = 1L;
private String productId;
private String configItemId;
private Long sequenceNum;
private Timestamp fromDate;
private String description;
private String longDescription;
private String configTypeId;
private String defaultConfigOptionId;
private Timestamp thruDate;
private Boolean isMandatory;

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
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

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getLongDescription() {
return longDescription;
}

public void setLongDescription(String  longDescription) {
this.longDescription = longDescription;
}

public String getConfigTypeId() {
return configTypeId;
}

public void setConfigTypeId(String  configTypeId) {
this.configTypeId = configTypeId;
}

public String getDefaultConfigOptionId() {
return defaultConfigOptionId;
}

public void setDefaultConfigOptionId(String  defaultConfigOptionId) {
this.defaultConfigOptionId = defaultConfigOptionId;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Boolean getIsMandatory() {
return isMandatory;
}

public void setIsMandatory(Boolean  isMandatory) {
this.isMandatory = isMandatory;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigMapper.map(this);
}
}
