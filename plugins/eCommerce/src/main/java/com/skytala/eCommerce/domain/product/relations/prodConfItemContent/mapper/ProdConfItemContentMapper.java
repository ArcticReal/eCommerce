package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;

public class ProdConfItemContentMapper  {


	public static Map<String, Object> map(ProdConfItemContent prodconfitemcontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodconfitemcontent.getConfigItemId() != null ){
			returnVal.put("configItemId",prodconfitemcontent.getConfigItemId());
}

		if(prodconfitemcontent.getContentId() != null ){
			returnVal.put("contentId",prodconfitemcontent.getContentId());
}

		if(prodconfitemcontent.getConfItemContentTypeId() != null ){
			returnVal.put("confItemContentTypeId",prodconfitemcontent.getConfItemContentTypeId());
}

		if(prodconfitemcontent.getFromDate() != null ){
			returnVal.put("fromDate",prodconfitemcontent.getFromDate());
}

		if(prodconfitemcontent.getThruDate() != null ){
			returnVal.put("thruDate",prodconfitemcontent.getThruDate());
}

		return returnVal;
}


	public static ProdConfItemContent map(Map<String, Object> fields) {

		ProdConfItemContent returnVal = new ProdConfItemContent();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("confItemContentTypeId") != null) {
			returnVal.setConfItemContentTypeId((String) fields.get("confItemContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProdConfItemContent mapstrstr(Map<String, String> fields) throws Exception {

		ProdConfItemContent returnVal = new ProdConfItemContent();

		if(fields.get("configItemId") != null) {
			returnVal.setConfigItemId((String) fields.get("configItemId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("confItemContentTypeId") != null) {
			returnVal.setConfItemContentTypeId((String) fields.get("confItemContentTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static ProdConfItemContent map(GenericValue val) {

ProdConfItemContent returnVal = new ProdConfItemContent();
		returnVal.setConfigItemId(val.getString("configItemId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setConfItemContentTypeId(val.getString("confItemContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProdConfItemContent map(HttpServletRequest request) throws Exception {

		ProdConfItemContent returnVal = new ProdConfItemContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("configItemId")) {
returnVal.setConfigItemId(request.getParameter("configItemId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("confItemContentTypeId"))  {
returnVal.setConfItemContentTypeId(request.getParameter("confItemContentTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
