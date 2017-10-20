package com.skytala.eCommerce.domain.accounting.relations.invoice.model.note;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.note.InvoiceNoteMapper;

public class InvoiceNote implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceId;
private String noteId;

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceNoteMapper.map(this);
}
}
