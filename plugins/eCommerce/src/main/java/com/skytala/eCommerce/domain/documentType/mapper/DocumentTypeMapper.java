package com.skytala.eCommerce.domain.documentType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.documentType.model.DocumentType;

public class DocumentTypeMapper  {


	public static Map<String, Object> map(DocumentType documenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(documenttype.getDocumentTypeId() != null ){
			returnVal.put("documentTypeId",documenttype.getDocumentTypeId());
}

		if(documenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",documenttype.getParentTypeId());
}

		if(documenttype.getHasTable() != null ){
			returnVal.put("hasTable",documenttype.getHasTable());
}

		if(documenttype.getDescription() != null ){
			returnVal.put("description",documenttype.getDescription());
}

		return returnVal;
}


	public static DocumentType map(Map<String, Object> fields) {

		DocumentType returnVal = new DocumentType();

		if(fields.get("documentTypeId") != null) {
			returnVal.setDocumentTypeId((String) fields.get("documentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DocumentType mapstrstr(Map<String, String> fields) throws Exception {

		DocumentType returnVal = new DocumentType();

		if(fields.get("documentTypeId") != null) {
			returnVal.setDocumentTypeId((String) fields.get("documentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DocumentType map(GenericValue val) {

DocumentType returnVal = new DocumentType();
		returnVal.setDocumentTypeId(val.getString("documentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static DocumentType map(HttpServletRequest request) throws Exception {

		DocumentType returnVal = new DocumentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("documentTypeId")) {
returnVal.setDocumentTypeId(request.getParameter("documentTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
