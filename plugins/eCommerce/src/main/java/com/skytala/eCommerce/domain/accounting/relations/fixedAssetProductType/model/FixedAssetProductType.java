package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProductType.mapper.FixedAssetProductTypeMapper;

public class FixedAssetProductType implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetProductTypeId;
private String description;

public String getFixedAssetProductTypeId() {
return fixedAssetProductTypeId;
}

public void setFixedAssetProductTypeId(String  fixedAssetProductTypeId) {
this.fixedAssetProductTypeId = fixedAssetProductTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetProductTypeMapper.map(this);
}
}
