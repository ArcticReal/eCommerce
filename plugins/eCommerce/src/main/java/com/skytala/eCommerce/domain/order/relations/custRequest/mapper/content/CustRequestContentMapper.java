package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.content.CustRequestContent;

public class CustRequestContentMapper  {


	public static Map<String, Object> map(CustRequestContent custrequestcontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestcontent.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestcontent.getCustRequestId());
}

		if(custrequestcontent.getContentId() != null ){
			returnVal.put("contentId",custrequestcontent.getContentId());
}

		if(custrequestcontent.getFromDate() != null ){
			returnVal.put("fromDate",custrequestcontent.getFromDate());
}

		if(custrequestcontent.getThruDate() != null ){
			returnVal.put("thruDate",custrequestcontent.getThruDate());
}

		return returnVal;
}


	public static CustRequestContent map(Map<String, Object> fields) {

		CustRequestContent returnVal = new CustRequestContent();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static CustRequestContent mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestContent returnVal = new CustRequestContent();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
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
	public static CustRequestContent map(GenericValue val) {

CustRequestContent returnVal = new CustRequestContent();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static CustRequestContent map(HttpServletRequest request) throws Exception {

		CustRequestContent returnVal = new CustRequestContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
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
