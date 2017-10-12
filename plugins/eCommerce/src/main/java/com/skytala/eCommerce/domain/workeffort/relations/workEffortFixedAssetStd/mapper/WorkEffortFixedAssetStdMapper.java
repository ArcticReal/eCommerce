package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetStd.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetStd.model.WorkEffortFixedAssetStd;

public class WorkEffortFixedAssetStdMapper  {


	public static Map<String, Object> map(WorkEffortFixedAssetStd workeffortfixedassetstd) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortfixedassetstd.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortfixedassetstd.getWorkEffortId());
}

		if(workeffortfixedassetstd.getFixedAssetTypeId() != null ){
			returnVal.put("fixedAssetTypeId",workeffortfixedassetstd.getFixedAssetTypeId());
}

		if(workeffortfixedassetstd.getEstimatedQuantity() != null ){
			returnVal.put("estimatedQuantity",workeffortfixedassetstd.getEstimatedQuantity());
}

		if(workeffortfixedassetstd.getEstimatedDuration() != null ){
			returnVal.put("estimatedDuration",workeffortfixedassetstd.getEstimatedDuration());
}

		if(workeffortfixedassetstd.getEstimatedCost() != null ){
			returnVal.put("estimatedCost",workeffortfixedassetstd.getEstimatedCost());
}

		return returnVal;
}


	public static WorkEffortFixedAssetStd map(Map<String, Object> fields) {

		WorkEffortFixedAssetStd returnVal = new WorkEffortFixedAssetStd();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("estimatedQuantity") != null) {
			returnVal.setEstimatedQuantity((BigDecimal) fields.get("estimatedQuantity"));
}

		if(fields.get("estimatedDuration") != null) {
			returnVal.setEstimatedDuration((BigDecimal) fields.get("estimatedDuration"));
}

		if(fields.get("estimatedCost") != null) {
			returnVal.setEstimatedCost((BigDecimal) fields.get("estimatedCost"));
}


		return returnVal;
 } 
	public static WorkEffortFixedAssetStd mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortFixedAssetStd returnVal = new WorkEffortFixedAssetStd();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("estimatedQuantity") != null) {
String buf;
buf = fields.get("estimatedQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedQuantity(bd);
}

		if(fields.get("estimatedDuration") != null) {
String buf;
buf = fields.get("estimatedDuration");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedDuration(bd);
}

		if(fields.get("estimatedCost") != null) {
String buf;
buf = fields.get("estimatedCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedCost(bd);
}


		return returnVal;
 } 
	public static WorkEffortFixedAssetStd map(GenericValue val) {

WorkEffortFixedAssetStd returnVal = new WorkEffortFixedAssetStd();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setFixedAssetTypeId(val.getString("fixedAssetTypeId"));
		returnVal.setEstimatedQuantity(val.getBigDecimal("estimatedQuantity"));
		returnVal.setEstimatedDuration(val.getBigDecimal("estimatedDuration"));
		returnVal.setEstimatedCost(val.getBigDecimal("estimatedCost"));


return returnVal;

}

public static WorkEffortFixedAssetStd map(HttpServletRequest request) throws Exception {

		WorkEffortFixedAssetStd returnVal = new WorkEffortFixedAssetStd();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("fixedAssetTypeId"))  {
returnVal.setFixedAssetTypeId(request.getParameter("fixedAssetTypeId"));
}
		if(paramMap.containsKey("estimatedQuantity"))  {
String buf = request.getParameter("estimatedQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedQuantity(bd);
}
		if(paramMap.containsKey("estimatedDuration"))  {
String buf = request.getParameter("estimatedDuration");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedDuration(bd);
}
		if(paramMap.containsKey("estimatedCost"))  {
String buf = request.getParameter("estimatedCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedCost(bd);
}
return returnVal;

}
}
