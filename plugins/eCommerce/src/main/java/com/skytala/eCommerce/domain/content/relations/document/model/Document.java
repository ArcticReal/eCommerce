package com.skytala.eCommerce.domain.content.relations.document.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.content.relations.document.mapper.DocumentMapper;

public class Document implements Serializable{

private static final long serialVersionUID = 1L;
private String documentId;
private String documentTypeId;
private Timestamp dateCreated;
private String comments;
private String documentLocation;
private String documentText;
private Object imageData;

public String getDocumentId() {
return documentId;
}

public void setDocumentId(String  documentId) {
this.documentId = documentId;
}

public String getDocumentTypeId() {
return documentTypeId;
}

public void setDocumentTypeId(String  documentTypeId) {
this.documentTypeId = documentTypeId;
}

public Timestamp getDateCreated() {
return dateCreated;
}

public void setDateCreated(Timestamp  dateCreated) {
this.dateCreated = dateCreated;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getDocumentLocation() {
return documentLocation;
}

public void setDocumentLocation(String  documentLocation) {
this.documentLocation = documentLocation;
}

public String getDocumentText() {
return documentText;
}

public void setDocumentText(String  documentText) {
this.documentText = documentText;
}

public Object getImageData() {
return imageData;
}

public void setImageData(Object  imageData) {
this.imageData = imageData;
}


public Map<String, Object> mapAttributeField() {
return DocumentMapper.map(this);
}
}
