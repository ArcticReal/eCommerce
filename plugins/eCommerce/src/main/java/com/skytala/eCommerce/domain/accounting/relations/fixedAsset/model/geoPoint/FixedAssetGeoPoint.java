package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.geoPoint;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.geoPoint.FixedAssetGeoPointMapper;

public class FixedAssetGeoPoint implements Serializable{

private static final long serialVersionUID = 1L;
private String fixedAssetId;
private String geoPointId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getGeoPointId() {
return geoPointId;
}

public void setGeoPointId(String  geoPointId) {
this.geoPointId = geoPointId;
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
return FixedAssetGeoPointMapper.map(this);
}
}
