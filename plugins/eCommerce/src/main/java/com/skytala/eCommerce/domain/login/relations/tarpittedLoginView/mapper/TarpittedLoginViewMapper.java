package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;

public class TarpittedLoginViewMapper  {


	public static Map<String, Object> map(TarpittedLoginView tarpittedloginview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(tarpittedloginview.getViewNameId() != null ){
			returnVal.put("viewNameId",tarpittedloginview.getViewNameId());
}

		if(tarpittedloginview.getUserLoginId() != null ){
			returnVal.put("userLoginId",tarpittedloginview.getUserLoginId());
}

		if(tarpittedloginview.getTarpitReleaseDateTime() != null ){
			returnVal.put("tarpitReleaseDateTime",tarpittedloginview.getTarpitReleaseDateTime());
}

		return returnVal;
}


	public static TarpittedLoginView map(Map<String, Object> fields) {

		TarpittedLoginView returnVal = new TarpittedLoginView();

		if(fields.get("viewNameId") != null) {
			returnVal.setViewNameId((String) fields.get("viewNameId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("tarpitReleaseDateTime") != null) {
			returnVal.setTarpitReleaseDateTime((long) fields.get("tarpitReleaseDateTime"));
}


		return returnVal;
 } 
	public static TarpittedLoginView mapstrstr(Map<String, String> fields) throws Exception {

		TarpittedLoginView returnVal = new TarpittedLoginView();

		if(fields.get("viewNameId") != null) {
			returnVal.setViewNameId((String) fields.get("viewNameId"));
}

		if(fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
}

		if(fields.get("tarpitReleaseDateTime") != null) {
String buf;
buf = fields.get("tarpitReleaseDateTime");
long ibuf = Long.parseLong(buf);
			returnVal.setTarpitReleaseDateTime(ibuf);
}


		return returnVal;
 } 
	public static TarpittedLoginView map(GenericValue val) {

TarpittedLoginView returnVal = new TarpittedLoginView();
		returnVal.setViewNameId(val.getString("viewNameId"));
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setTarpitReleaseDateTime(val.getLong("tarpitReleaseDateTime"));


return returnVal;

}

public static TarpittedLoginView map(HttpServletRequest request) throws Exception {

		TarpittedLoginView returnVal = new TarpittedLoginView();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("viewNameId")) {
returnVal.setViewNameId(request.getParameter("viewNameId"));
}

		if(paramMap.containsKey("userLoginId"))  {
returnVal.setUserLoginId(request.getParameter("userLoginId"));
}
		if(paramMap.containsKey("tarpitReleaseDateTime"))  {
String buf = request.getParameter("tarpitReleaseDateTime");
Long ibuf = Long.parseLong(buf);
returnVal.setTarpitReleaseDateTime(ibuf);
}
return returnVal;

}
}
