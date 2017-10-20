package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.workEffort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.workEffort.CustRequestWorkEffort;

public class CustRequestWorkEffortMapper  {


	public static Map<String, Object> map(CustRequestWorkEffort custrequestworkeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestworkeffort.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestworkeffort.getCustRequestId());
}

		if(custrequestworkeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",custrequestworkeffort.getWorkEffortId());
}

		return returnVal;
}


	public static CustRequestWorkEffort map(Map<String, Object> fields) {

		CustRequestWorkEffort returnVal = new CustRequestWorkEffort();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static CustRequestWorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestWorkEffort returnVal = new CustRequestWorkEffort();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static CustRequestWorkEffort map(GenericValue val) {

CustRequestWorkEffort returnVal = new CustRequestWorkEffort();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static CustRequestWorkEffort map(HttpServletRequest request) throws Exception {

		CustRequestWorkEffort returnVal = new CustRequestWorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
