package com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.commEventContentAssoc.mapper.CommEventContentAssocMapper;

public class CommEventContentAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String contentId;
private String communicationEventId;
private String commContentAssocTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Long sequenceNum;

public String getContentId() {
return contentId;
}

public void setContentId(String  contentId) {
this.contentId = contentId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}

public String getCommContentAssocTypeId() {
return commContentAssocTypeId;
}

public void setCommContentAssocTypeId(String  commContentAssocTypeId) {
this.commContentAssocTypeId = commContentAssocTypeId;
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
return CommEventContentAssocMapper.map(this);
}
}
