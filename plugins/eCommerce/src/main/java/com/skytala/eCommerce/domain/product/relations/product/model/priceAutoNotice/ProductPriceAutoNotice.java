package com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceAutoNotice.ProductPriceAutoNoticeMapper;

public class ProductPriceAutoNotice implements Serializable{

private static final long serialVersionUID = 1L;
private String productPriceNoticeId;
private String facilityId;
private Timestamp runDate;
private Timestamp fromDate;
private Timestamp thruDate;

public String getProductPriceNoticeId() {
return productPriceNoticeId;
}

public void setProductPriceNoticeId(String  productPriceNoticeId) {
this.productPriceNoticeId = productPriceNoticeId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public Timestamp getRunDate() {
return runDate;
}

public void setRunDate(Timestamp  runDate) {
this.runDate = runDate;
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
return ProductPriceAutoNoticeMapper.map(this);
}
}
