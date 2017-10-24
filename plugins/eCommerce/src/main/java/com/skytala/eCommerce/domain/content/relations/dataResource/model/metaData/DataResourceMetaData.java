package com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.metaData.DataResourceMetaDataMapper;

public class DataResourceMetaData implements Serializable{

private static final long serialVersionUID = 1L;
private String dataResourceId;
private String metaDataPredicateId;
private String metaDataValue;
private String dataSourceId;

public String getDataResourceId() {
return dataResourceId;
}

public void setDataResourceId(String  dataResourceId) {
this.dataResourceId = dataResourceId;
}

public String getMetaDataPredicateId() {
return metaDataPredicateId;
}

public void setMetaDataPredicateId(String  metaDataPredicateId) {
this.metaDataPredicateId = metaDataPredicateId;
}

public String getMetaDataValue() {
return metaDataValue;
}

public void setMetaDataValue(String  metaDataValue) {
this.metaDataValue = metaDataValue;
}

public String getDataSourceId() {
return dataSourceId;
}

public void setDataSourceId(String  dataSourceId) {
this.dataSourceId = dataSourceId;
}


public Map<String, Object> mapAttributeField() {
return DataResourceMetaDataMapper.map(this);
}
}
