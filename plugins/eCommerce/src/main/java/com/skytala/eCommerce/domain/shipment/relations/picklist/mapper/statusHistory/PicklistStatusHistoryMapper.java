package com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.statusHistory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.statusHistory.PicklistStatusHistory;

public class PicklistStatusHistoryMapper  {


	public static Map<String, Object> map(PicklistStatusHistory pickliststatushistory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(pickliststatushistory.getPicklistId() != null ){
			returnVal.put("picklistId",pickliststatushistory.getPicklistId());
}

		if(pickliststatushistory.getChangeDate() != null ){
			returnVal.put("changeDate",pickliststatushistory.getChangeDate());
}

		if(pickliststatushistory.getChangeUserLoginId() != null ){
			returnVal.put("changeUserLoginId",pickliststatushistory.getChangeUserLoginId());
}

		if(pickliststatushistory.getStatusId() != null ){
			returnVal.put("statusId",pickliststatushistory.getStatusId());
}

		if(pickliststatushistory.getStatusIdTo() != null ){
			returnVal.put("statusIdTo",pickliststatushistory.getStatusIdTo());
}

		return returnVal;
}


	public static PicklistStatusHistory map(Map<String, Object> fields) {

		PicklistStatusHistory returnVal = new PicklistStatusHistory();

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
}

		if(fields.get("changeDate") != null) {
			returnVal.setChangeDate((Timestamp) fields.get("changeDate"));
}

		if(fields.get("changeUserLoginId") != null) {
			returnVal.setChangeUserLoginId((String) fields.get("changeUserLoginId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusIdTo") != null) {
			returnVal.setStatusIdTo((String) fields.get("statusIdTo"));
}


		return returnVal;
 } 
	public static PicklistStatusHistory mapstrstr(Map<String, String> fields) throws Exception {

		PicklistStatusHistory returnVal = new PicklistStatusHistory();

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
}

		if(fields.get("changeDate") != null) {
String buf = fields.get("changeDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setChangeDate(ibuf);
}

		if(fields.get("changeUserLoginId") != null) {
			returnVal.setChangeUserLoginId((String) fields.get("changeUserLoginId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusIdTo") != null) {
			returnVal.setStatusIdTo((String) fields.get("statusIdTo"));
}


		return returnVal;
 } 
	public static PicklistStatusHistory map(GenericValue val) {

PicklistStatusHistory returnVal = new PicklistStatusHistory();
		returnVal.setPicklistId(val.getString("picklistId"));
		returnVal.setChangeDate(val.getTimestamp("changeDate"));
		returnVal.setChangeUserLoginId(val.getString("changeUserLoginId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusIdTo(val.getString("statusIdTo"));


return returnVal;

}

public static PicklistStatusHistory map(HttpServletRequest request) throws Exception {

		PicklistStatusHistory returnVal = new PicklistStatusHistory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("picklistId")) {
returnVal.setPicklistId(request.getParameter("picklistId"));
}

		if(paramMap.containsKey("changeDate"))  {
String buf = request.getParameter("changeDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setChangeDate(ibuf);
}
		if(paramMap.containsKey("changeUserLoginId"))  {
returnVal.setChangeUserLoginId(request.getParameter("changeUserLoginId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusIdTo"))  {
returnVal.setStatusIdTo(request.getParameter("statusIdTo"));
}
return returnVal;

}
}
