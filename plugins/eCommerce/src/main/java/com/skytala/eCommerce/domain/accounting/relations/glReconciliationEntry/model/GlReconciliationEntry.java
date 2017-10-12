package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.mapper.GlReconciliationEntryMapper;

public class GlReconciliationEntry implements Serializable{

private static final long serialVersionUID = 1L;
private String glReconciliationId;
private String acctgTransId;
private String acctgTransEntrySeqId;
private BigDecimal reconciledAmount;

public String getGlReconciliationId() {
return glReconciliationId;
}

public void setGlReconciliationId(String  glReconciliationId) {
this.glReconciliationId = glReconciliationId;
}

public String getAcctgTransId() {
return acctgTransId;
}

public void setAcctgTransId(String  acctgTransId) {
this.acctgTransId = acctgTransId;
}

public String getAcctgTransEntrySeqId() {
return acctgTransEntrySeqId;
}

public void setAcctgTransEntrySeqId(String  acctgTransEntrySeqId) {
this.acctgTransEntrySeqId = acctgTransEntrySeqId;
}

public BigDecimal getReconciledAmount() {
return reconciledAmount;
}

public void setReconciledAmount(BigDecimal  reconciledAmount) {
this.reconciledAmount = reconciledAmount;
}


public Map<String, Object> mapAttributeField() {
return GlReconciliationEntryMapper.map(this);
}
}
