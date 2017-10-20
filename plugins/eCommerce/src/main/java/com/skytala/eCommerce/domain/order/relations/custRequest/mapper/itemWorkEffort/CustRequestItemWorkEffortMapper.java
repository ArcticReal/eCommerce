package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemWorkEffort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort.CustRequestItemWorkEffort;

public class CustRequestItemWorkEffortMapper  {


	public static Map<String, Object> map(CustRequestItemWorkEffort custrequestitemworkeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestitemworkeffort.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestitemworkeffort.getCustRequestId());
}

		if(custrequestitemworkeffort.getCustRequestItemSeqId() != null ){
			returnVal.put("custRequestItemSeqId",custrequestitemworkeffort.getCustRequestItemSeqId());
}

		if(custrequestitemworkeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",custrequestitemworkeffort.getWorkEffortId());
}

		return returnVal;
}


	public static CustRequestItemWorkEffort map(Map<String, Object> fields) {

		CustRequestItemWorkEffort returnVal = new CustRequestItemWorkEffort();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static CustRequestItemWorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestItemWorkEffort returnVal = new CustRequestItemWorkEffort();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static CustRequestItemWorkEffort map(GenericValue val) {

CustRequestItemWorkEffort returnVal = new CustRequestItemWorkEffort();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestItemSeqId(val.getString("custRequestItemSeqId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static CustRequestItemWorkEffort map(HttpServletRequest request) throws Exception {

		CustRequestItemWorkEffort returnVal = new CustRequestItemWorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("custRequestItemSeqId"))  {
returnVal.setCustRequestItemSeqId(request.getParameter("custRequestItemSeqId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
