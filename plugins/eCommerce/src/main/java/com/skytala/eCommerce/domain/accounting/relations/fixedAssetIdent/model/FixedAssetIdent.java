package com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdent.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetIdent.mapper.FixedAssetIdentMapper;

public class FixedAssetIdent implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String fixedAssetIdentTypeId;
private String idValue;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getFixedAssetIdentTypeId() {
return fixedAssetIdentTypeId;
}

public void setFixedAssetIdentTypeId(String  fixedAssetIdentTypeId) {
this.fixedAssetIdentTypeId = fixedAssetIdentTypeId;
}

public String getIdValue() {
return idValue;
}

public void setIdValue(String  idValue) {
this.idValue = idValue;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetIdentMapper.map(this);
}
}
