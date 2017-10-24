package com.skytala.eCommerce.domain.content.relations.content.model.metaData;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.content.mapper.metaData.ContentMetaDataMapper;

public class ContentMetaData implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String metaDataPredicateId;
private String metaDataValue;
private String dataSourceId;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
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
return ContentMetaDataMapper.map(this);
}
}
