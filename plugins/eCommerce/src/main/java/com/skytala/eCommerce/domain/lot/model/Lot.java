package com.skytala.eCommerce.domain.lot.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.lot.mapper.LotMapper;

public class Lot implements Serializable{

private static final long serialVersionUID = 1L;
private String lotId;
private Timestamp creationDate;
private BigDecimal quantity;
private Timestamp expirationDate;

public String getLotId() {
return lotId;
}

public void setLotId(String  lotId) {
this.lotId = lotId;
}

public Timestamp getCreationDate() {
return creationDate;
}

public void setCreationDate(Timestamp  creationDate) {
this.creationDate = creationDate;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public Timestamp getExpirationDate() {
return expirationDate;
}

public void setExpirationDate(Timestamp  expirationDate) {
this.expirationDate = expirationDate;
}


public Map<String, Object> mapAttributeField() {
return LotMapper.map(this);
}
}
