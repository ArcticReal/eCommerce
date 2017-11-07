package com.skytala.eCommerce.domain.login.relations.protectedView.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;

public class ProtectedViewMapper  {


	public static Map<String, Object> map(ProtectedView protectedview) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(protectedview.getGroupId() != null ){
			returnVal.put("groupId",protectedview.getGroupId());
}

		if(protectedview.getViewNameId() != null ){
			returnVal.put("viewNameId",protectedview.getViewNameId());
}

		if(protectedview.getMaxHits() != null ){
			returnVal.put("maxHits",protectedview.getMaxHits());
}

		if(protectedview.getMaxHitsDuration() != null ){
			returnVal.put("maxHitsDuration",protectedview.getMaxHitsDuration());
}

		if(protectedview.getTarpitDuration() != null ){
			returnVal.put("tarpitDuration",protectedview.getTarpitDuration());
}

		return returnVal;
}


	public static ProtectedView map(Map<String, Object> fields) {

		ProtectedView returnVal = new ProtectedView();

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("viewNameId") != null) {
			returnVal.setViewNameId((String) fields.get("viewNameId"));
}

		if(fields.get("maxHits") != null) {
			returnVal.setMaxHits((long) fields.get("maxHits"));
}

		if(fields.get("maxHitsDuration") != null) {
			returnVal.setMaxHitsDuration((long) fields.get("maxHitsDuration"));
}

		if(fields.get("tarpitDuration") != null) {
			returnVal.setTarpitDuration((long) fields.get("tarpitDuration"));
}


		return returnVal;
 } 
	public static ProtectedView mapstrstr(Map<String, String> fields) throws Exception {

		ProtectedView returnVal = new ProtectedView();

		if(fields.get("groupId") != null) {
			returnVal.setGroupId((String) fields.get("groupId"));
}

		if(fields.get("viewNameId") != null) {
			returnVal.setViewNameId((String) fields.get("viewNameId"));
}

		if(fields.get("maxHits") != null) {
String buf;
buf = fields.get("maxHits");
long ibuf = Long.parseLong(buf);
			returnVal.setMaxHits(ibuf);
}

		if(fields.get("maxHitsDuration") != null) {
String buf;
buf = fields.get("maxHitsDuration");
long ibuf = Long.parseLong(buf);
			returnVal.setMaxHitsDuration(ibuf);
}

		if(fields.get("tarpitDuration") != null) {
String buf;
buf = fields.get("tarpitDuration");
long ibuf = Long.parseLong(buf);
			returnVal.setTarpitDuration(ibuf);
}


		return returnVal;
 } 
	public static ProtectedView map(GenericValue val) {

ProtectedView returnVal = new ProtectedView();
		returnVal.setGroupId(val.getString("groupId"));
		returnVal.setViewNameId(val.getString("viewNameId"));
		returnVal.setMaxHits(val.getLong("maxHits"));
		returnVal.setMaxHitsDuration(val.getLong("maxHitsDuration"));
		returnVal.setTarpitDuration(val.getLong("tarpitDuration"));


return returnVal;

}

public static ProtectedView map(HttpServletRequest request) throws Exception {

		ProtectedView returnVal = new ProtectedView();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("groupId")) {
returnVal.setGroupId(request.getParameter("groupId"));
}

		if(paramMap.containsKey("viewNameId"))  {
returnVal.setViewNameId(request.getParameter("viewNameId"));
}
		if(paramMap.containsKey("maxHits"))  {
String buf = request.getParameter("maxHits");
Long ibuf = Long.parseLong(buf);
returnVal.setMaxHits(ibuf);
}
		if(paramMap.containsKey("maxHitsDuration"))  {
String buf = request.getParameter("maxHitsDuration");
Long ibuf = Long.parseLong(buf);
returnVal.setMaxHitsDuration(ibuf);
}
		if(paramMap.containsKey("tarpitDuration"))  {
String buf = request.getParameter("tarpitDuration");
Long ibuf = Long.parseLong(buf);
returnVal.setTarpitDuration(ibuf);
}
return returnVal;

}
}
