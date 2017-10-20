package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeAttr.FixedAssetTypeAttrMapper;

public class FixedAssetTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetTypeId;
private String attrName;
private String description;

public String getFixedAssetTypeId() {
return fixedAssetTypeId;
}

public void setFixedAssetTypeId(String  fixedAssetTypeId) {
this.fixedAssetTypeId = fixedAssetTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetTypeAttrMapper.map(this);
}
}
