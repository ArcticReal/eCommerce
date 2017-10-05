package com.skytala.eCommerce.domain.picklist.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.picklist.model.Picklist;

public class PicklistMapper  {


	public static Map<String, Object> map(Picklist picklist) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(picklist.getPicklistId() != null ){
			returnVal.put("picklistId",picklist.getPicklistId());
}

		if(picklist.getDescription() != null ){
			returnVal.put("description",picklist.getDescription());
}

		if(picklist.getFacilityId() != null ){
			returnVal.put("facilityId",picklist.getFacilityId());
}

		if(picklist.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",picklist.getShipmentMethodTypeId());
}

		if(picklist.getStatusId() != null ){
			returnVal.put("statusId",picklist.getStatusId());
}

		if(picklist.getPicklistDate() != null ){
			returnVal.put("picklistDate",picklist.getPicklistDate());
}

		if(picklist.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",picklist.getCreatedByUserLogin());
}

		if(picklist.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",picklist.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static Picklist map(Map<String, Object> fields) {

		Picklist returnVal = new Picklist();

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("picklistDate") != null) {
			returnVal.setPicklistDate((Timestamp) fields.get("picklistDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Picklist mapstrstr(Map<String, String> fields) throws Exception {

		Picklist returnVal = new Picklist();

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("picklistDate") != null) {
String buf = fields.get("picklistDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPicklistDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Picklist map(GenericValue val) {

Picklist returnVal = new Picklist();
		returnVal.setPicklistId(val.getString("picklistId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPicklistDate(val.getTimestamp("picklistDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static Picklist map(HttpServletRequest request) throws Exception {

		Picklist returnVal = new Picklist();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("picklistId")) {
returnVal.setPicklistId(request.getParameter("picklistId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("picklistDate"))  {
String buf = request.getParameter("picklistDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPicklistDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
