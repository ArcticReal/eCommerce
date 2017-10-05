package com.skytala.eCommerce.domain.jobInterviewType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.jobInterviewType.mapper.JobInterviewTypeMapper;

public class JobInterviewType implements Serializable{

private static final long serialVersionUID = 1L;
private String jobInterviewTypeId;
private String description;

public String getJobInterviewTypeId() {
return jobInterviewTypeId;
}

public void setJobInterviewTypeId(String  jobInterviewTypeId) {
this.jobInterviewTypeId = jobInterviewTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return JobInterviewTypeMapper.map(this);
}
}
