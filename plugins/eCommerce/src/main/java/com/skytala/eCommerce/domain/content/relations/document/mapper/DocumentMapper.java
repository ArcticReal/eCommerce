package com.skytala.eCommerce.domain.content.relations.document.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.document.model.Document;

public class DocumentMapper  {


	public static Map<String, Object> map(Document document) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(document.getDocumentId() != null ){
			returnVal.put("documentId",document.getDocumentId());
}

		if(document.getDocumentTypeId() != null ){
			returnVal.put("documentTypeId",document.getDocumentTypeId());
}

		if(document.getDateCreated() != null ){
			returnVal.put("dateCreated",document.getDateCreated());
}

		if(document.getComments() != null ){
			returnVal.put("comments",document.getComments());
}

		if(document.getDocumentLocation() != null ){
			returnVal.put("documentLocation",document.getDocumentLocation());
}

		if(document.getDocumentText() != null ){
			returnVal.put("documentText",document.getDocumentText());
}

		if(document.getImageData() != null ){
			returnVal.put("imageData",document.getImageData());
}

		return returnVal;
}


	public static Document map(Map<String, Object> fields) {

		Document returnVal = new Document();

		if(fields.get("documentId") != null) {
			returnVal.setDocumentId((String) fields.get("documentId"));
}

		if(fields.get("documentTypeId") != null) {
			returnVal.setDocumentTypeId((String) fields.get("documentTypeId"));
}

		if(fields.get("dateCreated") != null) {
			returnVal.setDateCreated((Timestamp) fields.get("dateCreated"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("documentLocation") != null) {
			returnVal.setDocumentLocation((String) fields.get("documentLocation"));
}

		if(fields.get("documentText") != null) {
			returnVal.setDocumentText((String) fields.get("documentText"));
}

		if(fields.get("imageData") != null) {
			returnVal.setImageData((Object) fields.get("imageData"));
}


		return returnVal;
 } 
	public static Document mapstrstr(Map<String, String> fields) throws Exception {

		Document returnVal = new Document();

		if(fields.get("documentId") != null) {
			returnVal.setDocumentId((String) fields.get("documentId"));
}

		if(fields.get("documentTypeId") != null) {
			returnVal.setDocumentTypeId((String) fields.get("documentTypeId"));
}

		if(fields.get("dateCreated") != null) {
String buf = fields.get("dateCreated");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateCreated(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("documentLocation") != null) {
			returnVal.setDocumentLocation((String) fields.get("documentLocation"));
}

		if(fields.get("documentText") != null) {
			returnVal.setDocumentText((String) fields.get("documentText"));
}

		if(fields.get("imageData") != null) {
String buf = fields.get("imageData");
Object ibuf = buf;
returnVal.setImageData(ibuf);
}


		return returnVal;
 } 
	public static Document map(GenericValue val) {

Document returnVal = new Document();
		returnVal.setDocumentId(val.getString("documentId"));
		returnVal.setDocumentTypeId(val.getString("documentTypeId"));
		returnVal.setDateCreated(val.getTimestamp("dateCreated"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setDocumentLocation(val.getString("documentLocation"));
		returnVal.setDocumentText(val.getString("documentText"));
		returnVal.setImageData(val.get("imageData"));


return returnVal;

}

public static Document map(HttpServletRequest request) throws Exception {

		Document returnVal = new Document();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("documentId")) {
returnVal.setDocumentId(request.getParameter("documentId"));
}

		if(paramMap.containsKey("documentTypeId"))  {
returnVal.setDocumentTypeId(request.getParameter("documentTypeId"));
}
		if(paramMap.containsKey("dateCreated"))  {
String buf = request.getParameter("dateCreated");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateCreated(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("documentLocation"))  {
returnVal.setDocumentLocation(request.getParameter("documentLocation"));
}
		if(paramMap.containsKey("documentText"))  {
returnVal.setDocumentText(request.getParameter("documentText"));
}
		if(paramMap.containsKey("imageData"))  {
String buf = request.getParameter("imageData");
Object ibuf = buf;
returnVal.setImageData(ibuf);
}
return returnVal;

}
}
