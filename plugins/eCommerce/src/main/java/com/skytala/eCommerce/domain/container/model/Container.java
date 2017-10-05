package com.skytala.eCommerce.domain.container.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.container.mapper.ContainerMapper;

public class Container implements Serializable{

private static final long serialVersionUID = 1L;
private String containerId;
private String containerTypeId;
private String facilityId;
private String description;

public String getContainerId() {
return containerId;
}

public void setContainerId(String  containerId) {
this.containerId = containerId;
}

public String getContainerTypeId() {
return containerTypeId;
}

public void setContainerTypeId(String  containerTypeId) {
this.containerTypeId = containerTypeId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContainerMapper.map(this);
}
}
