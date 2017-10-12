package com.skytala.eCommerce.domain.content.relations.otherDataResource.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.otherDataResource.model.OtherDataResource;

public class OtherDataResourceMapper  {


	public static Map<String, Object> map(OtherDataResource otherdataresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(otherdataresource.getDataResourceId() != null ){
			returnVal.put("dataResourceId",otherdataresource.getDataResourceId());
}

		if(otherdataresource.getDataResourceContent() != null ){
			returnVal.put("dataResourceContent",otherdataresource.getDataResourceContent());
}

		return returnVal;
}


	public static OtherDataResource map(Map<String, Object> fields) {

		OtherDataResource returnVal = new OtherDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("dataResourceContent") != null) {
			returnVal.setDataResourceContent((byte[]) fields.get("dataResourceContent"));
}


		return returnVal;
 } 
	public static OtherDataResource mapstrstr(Map<String, String> fields) throws Exception {

		OtherDataResource returnVal = new OtherDataResource();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
}

		if(fields.get("dataResourceContent") != null) {
String buf = fields.get("dataResourceContent");
byte[] ibuf = buf.getBytes();
			returnVal.setDataResourceContent(ibuf);
}


		return returnVal;
 } 
	public static OtherDataResource map(GenericValue val) {

OtherDataResource returnVal = new OtherDataResource();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setDataResourceContent(val.getBytes("dataResourceContent"));


return returnVal;

}

public static OtherDataResource map(HttpServletRequest request) throws Exception {

		OtherDataResource returnVal = new OtherDataResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
}

		if(paramMap.containsKey("dataResourceContent"))  {
String buf = request.getParameter("dataResourceContent");
byte[] ibuf = buf.getBytes();
returnVal.setDataResourceContent(ibuf);
}
return returnVal;

}
}
