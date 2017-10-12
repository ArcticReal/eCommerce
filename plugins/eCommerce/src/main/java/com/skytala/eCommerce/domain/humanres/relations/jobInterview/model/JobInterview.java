package com.skytala.eCommerce.domain.humanres.relations.jobInterview.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.mapper.JobInterviewMapper;

public class JobInterview implements Serializable{

private static final long serialVersionUID = 1L;
private String jobInterviewId;
private String jobIntervieweePartyId;
private String jobRequisitionId;
private String jobInterviewerPartyId;
private String jobInterviewTypeId;
private String gradeSecuredEnumId;
private String jobInterviewResult;
private Timestamp jobInterviewDate;

public String getJobInterviewId() {
return jobInterviewId;
}

public void setJobInterviewId(String  jobInterviewId) {
this.jobInterviewId = jobInterviewId;
}

public String getJobIntervieweePartyId() {
return jobIntervieweePartyId;
}

public void setJobIntervieweePartyId(String  jobIntervieweePartyId) {
this.jobIntervieweePartyId = jobIntervieweePartyId;
}

public String getJobRequisitionId() {
return jobRequisitionId;
}

public void setJobRequisitionId(String  jobRequisitionId) {
this.jobRequisitionId = jobRequisitionId;
}

public String getJobInterviewerPartyId() {
return jobInterviewerPartyId;
}

public void setJobInterviewerPartyId(String  jobInterviewerPartyId) {
this.jobInterviewerPartyId = jobInterviewerPartyId;
}

public String getJobInterviewTypeId() {
return jobInterviewTypeId;
}

public void setJobInterviewTypeId(String  jobInterviewTypeId) {
this.jobInterviewTypeId = jobInterviewTypeId;
}

public String getGradeSecuredEnumId() {
return gradeSecuredEnumId;
}

public void setGradeSecuredEnumId(String  gradeSecuredEnumId) {
this.gradeSecuredEnumId = gradeSecuredEnumId;
}

public String getJobInterviewResult() {
return jobInterviewResult;
}

public void setJobInterviewResult(String  jobInterviewResult) {
this.jobInterviewResult = jobInterviewResult;
}

public Timestamp getJobInterviewDate() {
return jobInterviewDate;
}

public void setJobInterviewDate(Timestamp  jobInterviewDate) {
this.jobInterviewDate = jobInterviewDate;
}


public Map<String, Object> mapAttributeField() {
return JobInterviewMapper.map(this);
}
}
