package com.skytala.eCommerce.domain.content.relations.documentTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.model.DocumentTypeAttr;

public class DocumentTypeAttrMapper  {


	public static Map<String, Object> map(DocumentTypeAttr documenttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(documenttypeattr.getDocumentTypeId() != null ){
			returnVal.put("documentTypeId",documenttypeattr.getDocumentTypeId());
}

		if(documenttypeattr.getAttrName() != null ){
			returnVal.put("attrName",documenttypeattr.getAttrName());
}

		if(documenttypeattr.getDescription() != null ){
			returnVal.put("description",documenttypeattr.getDescription());
}

		return returnVal;
}


	public static DocumentTypeAttr map(Map<String, Object> fields) {

		DocumentTypeAttr returnVal = new DocumentTypeAttr();

		if(fields.get("documentTypeId") != null) {
			returnVal.setDocumentTypeId((String) fields.get("documentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DocumentTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		DocumentTypeAttr returnVal = new DocumentTypeAttr();

		if(fields.get("documentTypeId") != null) {
			returnVal.setDocumentTypeId((String) fields.get("documentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DocumentTypeAttr map(GenericValue val) {

DocumentTypeAttr returnVal = new DocumentTypeAttr();
		returnVal.setDocumentTypeId(val.getString("documentTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static DocumentTypeAttr map(HttpServletRequest request) throws Exception {

		DocumentTypeAttr returnVal = new DocumentTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("documentTypeId")) {
returnVal.setDocumentTypeId(request.getParameter("documentTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
