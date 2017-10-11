package com.skytala.eCommerce.domain.party.relations.addressMatchMap.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.mapper.AddressMatchMapMapper;

public class AddressMatchMap implements Serializable{

private static final long serialVersionUID = 1L;
private String mapKey;
private String mapValue;
private Long sequenceNum;

public String getMapKey() {
return mapKey;
}

public void setMapKey(String  mapKey) {
this.mapKey = mapKey;
}

public String getMapValue() {
return mapValue;
}

public void setMapValue(String  mapValue) {
this.mapValue = mapValue;
}

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return AddressMatchMapMapper.map(this);
}
}
