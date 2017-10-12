package com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdentType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdentType.mapper.FixedAssetIdentTypeMapper;

public class FixedAssetIdentType implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetIdentTypeId;
private String description;

public String getFixedAssetIdentTypeId() {
return fixedAssetIdentTypeId;
}

public void setFixedAssetIdentTypeId(String  fixedAssetIdentTypeId) {
this.fixedAssetIdentTypeId = fixedAssetIdentTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetIdentTypeMapper.map(this);
}
}
