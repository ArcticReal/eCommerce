package com.skytala.eCommerce.domain.shipment.relations.shipment.model.status;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.status.ShipmentStatusMapper;

public class ShipmentStatus implements Serializable{

private static final long serialVersionUID = 1L;
private String statusId;
private String shipmentId;
private Timestamp statusDate;
private String changeByUserLoginId;

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public Timestamp getStatusDate() {
return statusDate;
}

public void setStatusDate(Timestamp  statusDate) {
this.statusDate = statusDate;
}

public String getChangeByUserLoginId() {
return changeByUserLoginId;
}

public void setChangeByUserLoginId(String  changeByUserLoginId) {
this.changeByUserLoginId = changeByUserLoginId;
}


public Map<String, Object> mapAttributeField() {
return ShipmentStatusMapper.map(this);
}
}
