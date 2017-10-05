package com.skytala.eCommerce.domain.custRequestStatus.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.custRequestStatus.model.CustRequestStatus;

public class CustRequestStatusMapper  {


	public static Map<String, Object> map(CustRequestStatus custrequeststatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequeststatus.getCustRequestStatusId() != null ){
			returnVal.put("custRequestStatusId",custrequeststatus.getCustRequestStatusId());
}

		if(custrequeststatus.getStatusId() != null ){
			returnVal.put("statusId",custrequeststatus.getStatusId());
}

		if(custrequeststatus.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequeststatus.getCustRequestId());
}

		if(custrequeststatus.getCustRequestItemSeqId() != null ){
			returnVal.put("custRequestItemSeqId",custrequeststatus.getCustRequestItemSeqId());
}

		if(custrequeststatus.getStatusDate() != null ){
			returnVal.put("statusDate",custrequeststatus.getStatusDate());
}

		if(custrequeststatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",custrequeststatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static CustRequestStatus map(Map<String, Object> fields) {

		CustRequestStatus returnVal = new CustRequestStatus();

		if(fields.get("custRequestStatusId") != null) {
			returnVal.setCustRequestStatusId((String) fields.get("custRequestStatusId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static CustRequestStatus mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestStatus returnVal = new CustRequestStatus();

		if(fields.get("custRequestStatusId") != null) {
			returnVal.setCustRequestStatusId((String) fields.get("custRequestStatusId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static CustRequestStatus map(GenericValue val) {

CustRequestStatus returnVal = new CustRequestStatus();
		returnVal.setCustRequestStatusId(val.getString("custRequestStatusId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestItemSeqId(val.getString("custRequestItemSeqId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static CustRequestStatus map(HttpServletRequest request) throws Exception {

		CustRequestStatus returnVal = new CustRequestStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestStatusId")) {
returnVal.setCustRequestStatusId(request.getParameter("custRequestStatusId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("custRequestId"))  {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}
		if(paramMap.containsKey("custRequestItemSeqId"))  {
returnVal.setCustRequestItemSeqId(request.getParameter("custRequestItemSeqId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
