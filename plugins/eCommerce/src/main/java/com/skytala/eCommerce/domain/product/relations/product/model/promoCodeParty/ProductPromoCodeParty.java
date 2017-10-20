package com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeParty.ProductPromoCodePartyMapper;

public class ProductPromoCodeParty implements Serializable{

private static final long serialVersionUID = 1L;
private String productPromoCodeId;
private String partyId;

public String getProductPromoCodeId() {
return productPromoCodeId;
}

public void setProductPromoCodeId(String  productPromoCodeId) {
this.productPromoCodeId = productPromoCodeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}


public Map<String, Object> mapAttributeField() {
return ProductPromoCodePartyMapper.map(this);
}
}
