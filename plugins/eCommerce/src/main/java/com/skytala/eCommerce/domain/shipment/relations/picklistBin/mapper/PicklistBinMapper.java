package com.skytala.eCommerce.domain.shipment.relations.picklistBin.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklistBin.model.PicklistBin;

public class PicklistBinMapper  {


	public static Map<String, Object> map(PicklistBin picklistbin) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(picklistbin.getPicklistBinId() != null ){
			returnVal.put("picklistBinId",picklistbin.getPicklistBinId());
}

		if(picklistbin.getPicklistId() != null ){
			returnVal.put("picklistId",picklistbin.getPicklistId());
}

		if(picklistbin.getBinLocationNumber() != null ){
			returnVal.put("binLocationNumber",picklistbin.getBinLocationNumber());
}

		if(picklistbin.getPrimaryOrderId() != null ){
			returnVal.put("primaryOrderId",picklistbin.getPrimaryOrderId());
}

		if(picklistbin.getPrimaryShipGroupSeqId() != null ){
			returnVal.put("primaryShipGroupSeqId",picklistbin.getPrimaryShipGroupSeqId());
}

		return returnVal;
}


	public static PicklistBin map(Map<String, Object> fields) {

		PicklistBin returnVal = new PicklistBin();

		if(fields.get("picklistBinId") != null) {
			returnVal.setPicklistBinId((String) fields.get("picklistBinId"));
}

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
}

		if(fields.get("binLocationNumber") != null) {
			returnVal.setBinLocationNumber((long) fields.get("binLocationNumber"));
}

		if(fields.get("primaryOrderId") != null) {
			returnVal.setPrimaryOrderId((String) fields.get("primaryOrderId"));
}

		if(fields.get("primaryShipGroupSeqId") != null) {
			returnVal.setPrimaryShipGroupSeqId((String) fields.get("primaryShipGroupSeqId"));
}


		return returnVal;
 } 
	public static PicklistBin mapstrstr(Map<String, String> fields) throws Exception {

		PicklistBin returnVal = new PicklistBin();

		if(fields.get("picklistBinId") != null) {
			returnVal.setPicklistBinId((String) fields.get("picklistBinId"));
}

		if(fields.get("picklistId") != null) {
			returnVal.setPicklistId((String) fields.get("picklistId"));
}

		if(fields.get("binLocationNumber") != null) {
String buf;
buf = fields.get("binLocationNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setBinLocationNumber(ibuf);
}

		if(fields.get("primaryOrderId") != null) {
			returnVal.setPrimaryOrderId((String) fields.get("primaryOrderId"));
}

		if(fields.get("primaryShipGroupSeqId") != null) {
			returnVal.setPrimaryShipGroupSeqId((String) fields.get("primaryShipGroupSeqId"));
}


		return returnVal;
 } 
	public static PicklistBin map(GenericValue val) {

PicklistBin returnVal = new PicklistBin();
		returnVal.setPicklistBinId(val.getString("picklistBinId"));
		returnVal.setPicklistId(val.getString("picklistId"));
		returnVal.setBinLocationNumber(val.getLong("binLocationNumber"));
		returnVal.setPrimaryOrderId(val.getString("primaryOrderId"));
		returnVal.setPrimaryShipGroupSeqId(val.getString("primaryShipGroupSeqId"));


return returnVal;

}

public static PicklistBin map(HttpServletRequest request) throws Exception {

		PicklistBin returnVal = new PicklistBin();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("picklistBinId")) {
returnVal.setPicklistBinId(request.getParameter("picklistBinId"));
}

		if(paramMap.containsKey("picklistId"))  {
returnVal.setPicklistId(request.getParameter("picklistId"));
}
		if(paramMap.containsKey("binLocationNumber"))  {
String buf = request.getParameter("binLocationNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setBinLocationNumber(ibuf);
}
		if(paramMap.containsKey("primaryOrderId"))  {
returnVal.setPrimaryOrderId(request.getParameter("primaryOrderId"));
}
		if(paramMap.containsKey("primaryShipGroupSeqId"))  {
returnVal.setPrimaryShipGroupSeqId(request.getParameter("primaryShipGroupSeqId"));
}
return returnVal;

}
}
