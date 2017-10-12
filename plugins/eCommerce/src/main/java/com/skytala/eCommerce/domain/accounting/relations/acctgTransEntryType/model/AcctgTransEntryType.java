package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.mapper.AcctgTransEntryTypeMapper;

public class AcctgTransEntryType implements Serializable{

private static final long serialVersionUID = 1L;
private String acctgTransEntryTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getAcctgTransEntryTypeId() {
return acctgTransEntryTypeId;
}

public void setAcctgTransEntryTypeId(String  acctgTransEntryTypeId) {
this.acctgTransEntryTypeId = acctgTransEntryTypeId;
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
return AcctgTransEntryTypeMapper.map(this);
}
}
