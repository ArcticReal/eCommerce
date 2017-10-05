package com.skytala.eCommerce.domain.trainingClassType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.trainingClassType.mapper.TrainingClassTypeMapper;

public class TrainingClassType implements Serializable{

private static final long serialVersionUID = 1L;
private String trainingClassTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getTrainingClassTypeId() {
return trainingClassTypeId;
}

public void setTrainingClassTypeId(String  trainingClassTypeId) {
this.trainingClassTypeId = trainingClassTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TrainingClassTypeMapper.map(this);
}
}
