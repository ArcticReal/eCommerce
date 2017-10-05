package com.skytala.eCommerce.domain.rateType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.rateType.mapper.RateTypeMapper;

public class RateType implements Serializable{

private static final long serialVersionUID = 1L;
private String rateTypeId;
private String description;

public String getRateTypeId() {
return rateTypeId;
}

public void setRateTypeId(String  rateTypeId) {
this.rateTypeId = rateTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return RateTypeMapper.map(this);
}
}
