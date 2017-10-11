package com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.mapper.ProdCatalogCategoryMapper;

public class ProdCatalogCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String prodCatalogId;
private String productCategoryId;
private String prodCatalogCategoryTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getProdCatalogId() {
return prodCatalogId;
}

public void setProdCatalogId(String  prodCatalogId) {
this.prodCatalogId = prodCatalogId;
}

public String getProductCategoryId() {
return productCategoryId;
}

public void setProductCategoryId(String  productCategoryId) {
this.productCategoryId = productCategoryId;
}

public String getProdCatalogCategoryTypeId() {
return prodCatalogCategoryTypeId;
}

public void setProdCatalogCategoryTypeId(String  prodCatalogCategoryTypeId) {
this.prodCatalogCategoryTypeId = prodCatalogCategoryTypeId;
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
return ProdCatalogCategoryMapper.map(this);
}
}
