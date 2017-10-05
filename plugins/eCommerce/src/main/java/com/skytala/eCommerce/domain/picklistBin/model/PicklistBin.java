package com.skytala.eCommerce.domain.picklistBin.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.picklistBin.mapper.PicklistBinMapper;

public class PicklistBin implements Serializable{

private static final long serialVersionUID = 1L;
private String picklistBinId;
private String picklistId;
private Long binLocationNumber;
private String primaryOrderId;
private String primaryShipGroupSeqId;

public String getPicklistBinId() {
return picklistBinId;
}

public void setPicklistBinId(String  picklistBinId) {
this.picklistBinId = picklistBinId;
}

public String getPicklistId() {
return picklistId;
}

public void setPicklistId(String  picklistId) {
this.picklistId = picklistId;
}

public Long getBinLocationNumber() {
return binLocationNumber;
}

public void setBinLocationNumber(Long  binLocationNumber) {
this.binLocationNumber = binLocationNumber;
}

public String getPrimaryOrderId() {
return primaryOrderId;
}

public void setPrimaryOrderId(String  primaryOrderId) {
this.primaryOrderId = primaryOrderId;
}

public String getPrimaryShipGroupSeqId() {
return primaryShipGroupSeqId;
}

public void setPrimaryShipGroupSeqId(String  primaryShipGroupSeqId) {
this.primaryShipGroupSeqId = primaryShipGroupSeqId;
}


public Map<String, Object> mapAttributeField() {
return PicklistBinMapper.map(this);
}
}
