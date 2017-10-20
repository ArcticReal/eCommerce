package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.depMethod.FixedAssetDepMethodMapper;

public class FixedAssetDepMethod implements Serializable{

private static final long serialVersionUID = 1L;
private String depreciationCustomMethodId;
private String fixedAssetId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getDepreciationCustomMethodId() {
return depreciationCustomMethodId;
}

public void setDepreciationCustomMethodId(String  depreciationCustomMethodId) {
this.depreciationCustomMethodId = depreciationCustomMethodId;
}

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return FixedAssetDepMethodMapper.map(this);
}
}
