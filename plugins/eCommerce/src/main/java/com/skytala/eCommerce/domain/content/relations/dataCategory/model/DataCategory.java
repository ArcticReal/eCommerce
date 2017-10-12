package com.skytala.eCommerce.domain.content.relations.dataCategory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataCategory.mapper.DataCategoryMapper;

public class DataCategory implements Serializable{

private static final long serialVersionUID = 1L;
private String dataCategoryId;
private String parentCategoryId;
private String categoryName;

public String getDataCategoryId() {
return dataCategoryId;
}

public void setDataCategoryId(String  dataCategoryId) {
this.dataCategoryId = dataCategoryId;
}

public String getParentCategoryId() {
return parentCategoryId;
}

public void setParentCategoryId(String  parentCategoryId) {
this.parentCategoryId = parentCategoryId;
}

public String getCategoryName() {
return categoryName;
}

public void setCategoryName(String  categoryName) {
this.categoryName = categoryName;
}


public Map<String, Object> mapAttributeField() {
return DataCategoryMapper.map(this);
}
}
