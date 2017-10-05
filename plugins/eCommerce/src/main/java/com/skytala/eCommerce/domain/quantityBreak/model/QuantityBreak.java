package com.skytala.eCommerce.domain.quantityBreak.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.quantityBreak.mapper.QuantityBreakMapper;

public class QuantityBreak implements Serializable{

private static final long serialVersionUID = 1L;
private String quantityBreakId;
private String quantityBreakTypeId;
private BigDecimal fromQuantity;
private BigDecimal thruQuantity;

public String getQuantityBreakId() {
return quantityBreakId;
}

public void setQuantityBreakId(String  quantityBreakId) {
this.quantityBreakId = quantityBreakId;
}

public String getQuantityBreakTypeId() {
return quantityBreakTypeId;
}

public void setQuantityBreakTypeId(String  quantityBreakTypeId) {
this.quantityBreakTypeId = quantityBreakTypeId;
}

public BigDecimal getFromQuantity() {
return fromQuantity;
}

public void setFromQuantity(BigDecimal  fromQuantity) {
this.fromQuantity = fromQuantity;
}

public BigDecimal getThruQuantity() {
return thruQuantity;
}

public void setThruQuantity(BigDecimal  thruQuantity) {
this.thruQuantity = thruQuantity;
}


public Map<String, Object> mapAttributeField() {
return QuantityBreakMapper.map(this);
}
}
