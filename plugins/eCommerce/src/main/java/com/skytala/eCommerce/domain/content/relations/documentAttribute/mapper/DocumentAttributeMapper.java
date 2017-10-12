package com.skytala.eCommerce.domain.content.relations.documentAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.documentAttribute.model.DocumentAttribute;

public class DocumentAttributeMapper  {


	public static Map<String, Object> map(DocumentAttribute documentattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(documentattribute.getDocumentId() != null ){
			returnVal.put("documentId",documentattribute.getDocumentId());
}

		if(documentattribute.getAttrName() != null ){
			returnVal.put("attrName",documentattribute.getAttrName());
}

		if(documentattribute.getAttrValue() != null ){
			returnVal.put("attrValue",documentattribute.getAttrValue());
}

		if(documentattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",documentattribute.getAttrDescription());
}

		return returnVal;
}


	public static DocumentAttribute map(Map<String, Object> fields) {

		DocumentAttribute returnVal = new DocumentAttribute();

		if(fields.get("documentId") != null) {
			returnVal.setDocumentId((String) fields.get("documentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static DocumentAttribute mapstrstr(Map<String, String> fields) throws Exception {

		DocumentAttribute returnVal = new DocumentAttribute();

		if(fields.get("documentId") != null) {
			returnVal.setDocumentId((String) fields.get("documentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static DocumentAttribute map(GenericValue val) {

DocumentAttribute returnVal = new DocumentAttribute();
		returnVal.setDocumentId(val.getString("documentId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static DocumentAttribute map(HttpServletRequest request) throws Exception {

		DocumentAttribute returnVal = new DocumentAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("documentId")) {
returnVal.setDocumentId(request.getParameter("documentId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
