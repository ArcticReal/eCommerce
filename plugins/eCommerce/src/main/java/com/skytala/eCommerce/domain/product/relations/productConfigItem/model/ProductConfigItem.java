package com.skytala.eCommerce.domain.product.relations.productConfigItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.mapper.ProductConfigItemMapper;

public class ProductConfigItem implements Serializable{

private static final long serialVersionUID = 1L;
private String configItemId;
private String configItemTypeId;
private String configItemName;
private String description;
private String longDescription;
private String imageUrl;

public String getConfigItemId() {
return configItemId;
}

public void setConfigItemId(String  configItemId) {
this.configItemId = configItemId;
}

public String getConfigItemTypeId() {
return configItemTypeId;
}

public void setConfigItemTypeId(String  configItemTypeId) {
this.configItemTypeId = configItemTypeId;
}

public String getConfigItemName() {
return configItemName;
}

public void setConfigItemName(String  configItemName) {
this.configItemName = configItemName;
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

public String getImageUrl() {
return imageUrl;
}

public void setImageUrl(String  imageUrl) {
this.imageUrl = imageUrl;
}


public Map<String, Object> mapAttributeField() {
return ProductConfigItemMapper.map(this);
}
}
