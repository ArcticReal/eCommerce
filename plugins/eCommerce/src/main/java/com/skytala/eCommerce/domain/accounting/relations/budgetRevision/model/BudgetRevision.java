package com.skytala.eCommerce.domain.accounting.relations.budgetRevision.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevision.mapper.BudgetRevisionMapper;

public class BudgetRevision implements Serializable{

private static final long serialVersionUID = 1L;
private String budgetId;
private String revisionSeqId;
private Timestamp dateRevised;

public String getBudgetId() {
return budgetId;
}

public void setBudgetId(String  budgetId) {
this.budgetId = budgetId;
}

public String getRevisionSeqId() {
return revisionSeqId;
}

public void setRevisionSeqId(String  revisionSeqId) {
this.revisionSeqId = revisionSeqId;
}

public Timestamp getDateRevised() {
return dateRevised;
}

public void setDateRevised(Timestamp  dateRevised) {
this.dateRevised = dateRevised;
}


public Map<String, Object> mapAttributeField() {
return BudgetRevisionMapper.map(this);
}
}
