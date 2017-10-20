package com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.mapper.invFacility.ProdCatalogInvFacilityMapper;

public class ProdCatalogInvFacility implements Serializable{

private static final long serialVersionUID = 1L;
private String prodCatalogId;
private String facilityId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getProdCatalogId() {
return prodCatalogId;
}

public void setProdCatalogId(String  prodCatalogId) {
this.prodCatalogId = prodCatalogId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
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

public Long getSequenceNum() {
return sequenceNum;
}

public void setSequenceNum(Long  sequenceNum) {
this.sequenceNum = sequenceNum;
}


public Map<String, Object> mapAttributeField() {
return ProdCatalogInvFacilityMapper.map(this);
}
}
