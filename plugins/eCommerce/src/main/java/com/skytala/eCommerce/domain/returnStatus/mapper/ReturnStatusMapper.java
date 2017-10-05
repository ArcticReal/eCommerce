package com.skytala.eCommerce.domain.returnStatus.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnStatus.model.ReturnStatus;

public class ReturnStatusMapper  {


	public static Map<String, Object> map(ReturnStatus returnstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnstatus.getReturnStatusId() != null ){
			returnVal.put("returnStatusId",returnstatus.getReturnStatusId());
}

		if(returnstatus.getStatusId() != null ){
			returnVal.put("statusId",returnstatus.getStatusId());
}

		if(returnstatus.getReturnId() != null ){
			returnVal.put("returnId",returnstatus.getReturnId());
}

		if(returnstatus.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",returnstatus.getReturnItemSeqId());
}

		if(returnstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",returnstatus.getChangeByUserLoginId());
}

		if(returnstatus.getStatusDatetime() != null ){
			returnVal.put("statusDatetime",returnstatus.getStatusDatetime());
}

		return returnVal;
}


	public static ReturnStatus map(Map<String, Object> fields) {

		ReturnStatus returnVal = new ReturnStatus();

		if(fields.get("returnStatusId") != null) {
			returnVal.setReturnStatusId((String) fields.get("returnStatusId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}

		if(fields.get("statusDatetime") != null) {
			returnVal.setStatusDatetime((Timestamp) fields.get("statusDatetime"));
}


		return returnVal;
 } 
	public static ReturnStatus mapstrstr(Map<String, String> fields) throws Exception {

		ReturnStatus returnVal = new ReturnStatus();

		if(fields.get("returnStatusId") != null) {
			returnVal.setReturnStatusId((String) fields.get("returnStatusId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}

		if(fields.get("statusDatetime") != null) {
String buf = fields.get("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDatetime(ibuf);
}


		return returnVal;
 } 
	public static ReturnStatus map(GenericValue val) {

ReturnStatus returnVal = new ReturnStatus();
		returnVal.setReturnStatusId(val.getString("returnStatusId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));
		returnVal.setStatusDatetime(val.getTimestamp("statusDatetime"));


return returnVal;

}

public static ReturnStatus map(HttpServletRequest request) throws Exception {

		ReturnStatus returnVal = new ReturnStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnStatusId")) {
returnVal.setReturnStatusId(request.getParameter("returnStatusId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("returnId"))  {
returnVal.setReturnId(request.getParameter("returnId"));
}
		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
		if(paramMap.containsKey("statusDatetime"))  {
String buf = request.getParameter("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDatetime(ibuf);
}
return returnVal;

}
}
