package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.depMethod;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;

public class FixedAssetDepMethodMapper  {


	public static Map<String, Object> map(FixedAssetDepMethod fixedassetdepmethod) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetdepmethod.getDepreciationCustomMethodId() != null ){
			returnVal.put("depreciationCustomMethodId",fixedassetdepmethod.getDepreciationCustomMethodId());
}

		if(fixedassetdepmethod.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetdepmethod.getFixedAssetId());
}

		if(fixedassetdepmethod.getFromDate() != null ){
			returnVal.put("fromDate",fixedassetdepmethod.getFromDate());
}

		if(fixedassetdepmethod.getThruDate() != null ){
			returnVal.put("thruDate",fixedassetdepmethod.getThruDate());
}

		return returnVal;
}


	public static FixedAssetDepMethod map(Map<String, Object> fields) {

		FixedAssetDepMethod returnVal = new FixedAssetDepMethod();

		if(fields.get("depreciationCustomMethodId") != null) {
			returnVal.setDepreciationCustomMethodId((String) fields.get("depreciationCustomMethodId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static FixedAssetDepMethod mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetDepMethod returnVal = new FixedAssetDepMethod();

		if(fields.get("depreciationCustomMethodId") != null) {
			returnVal.setDepreciationCustomMethodId((String) fields.get("depreciationCustomMethodId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static FixedAssetDepMethod map(GenericValue val) {

FixedAssetDepMethod returnVal = new FixedAssetDepMethod();
		returnVal.setDepreciationCustomMethodId(val.getString("depreciationCustomMethodId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FixedAssetDepMethod map(HttpServletRequest request) throws Exception {

		FixedAssetDepMethod returnVal = new FixedAssetDepMethod();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("depreciationCustomMethodId")) {
returnVal.setDepreciationCustomMethodId(request.getParameter("depreciationCustomMethodId"));
}

		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
