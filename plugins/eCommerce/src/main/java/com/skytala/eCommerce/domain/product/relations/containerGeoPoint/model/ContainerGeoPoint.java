package com.skytala.eCommerce.domain.product.relations.containerGeoPoint.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.mapper.ContainerGeoPointMapper;

public class ContainerGeoPoint implements Serializable{

private static final long serialVersionUID = 1L;
private String containerId;
private String geoPointId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getContainerId() {
return containerId;
}

public void setContainerId(String  containerId) {
this.containerId = containerId;
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
return ContainerGeoPointMapper.map(this);
}
}
