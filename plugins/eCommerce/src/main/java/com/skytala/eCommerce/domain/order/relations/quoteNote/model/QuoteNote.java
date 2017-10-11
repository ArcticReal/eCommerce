package com.skytala.eCommerce.domain.order.relations.quoteNote.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.quoteNote.mapper.QuoteNoteMapper;

public class QuoteNote implements Serializable{

private static final long serialVersionUID = 1L;
private String quoteId;
private String noteId;

public String getQuoteId() {
return quoteId;
}

public void setQuoteId(String  quoteId) {
this.quoteId = quoteId;
}

public String getNoteId() {
return noteId;
}

public void setNoteId(String  noteId) {
this.noteId = noteId;
}


public Map<String, Object> mapAttributeField() {
return QuoteNoteMapper.map(this);
}
}
