package com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortCostCalc.model.WorkEffortCostCalc;

public class WorkEffortCostCalcMapper  {


	public static Map<String, Object> map(WorkEffortCostCalc workeffortcostcalc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortcostcalc.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortcostcalc.getWorkEffortId());
}

		if(workeffortcostcalc.getCostComponentTypeId() != null ){
			returnVal.put("costComponentTypeId",workeffortcostcalc.getCostComponentTypeId());
}

		if(workeffortcostcalc.getCostComponentCalcId() != null ){
			returnVal.put("costComponentCalcId",workeffortcostcalc.getCostComponentCalcId());
}

		if(workeffortcostcalc.getFromDate() != null ){
			returnVal.put("fromDate",workeffortcostcalc.getFromDate());
}

		if(workeffortcostcalc.getThruDate() != null ){
			returnVal.put("thruDate",workeffortcostcalc.getThruDate());
}

		return returnVal;
}


	public static WorkEffortCostCalc map(Map<String, Object> fields) {

		WorkEffortCostCalc returnVal = new WorkEffortCostCalc();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static WorkEffortCostCalc mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortCostCalc returnVal = new WorkEffortCostCalc();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("costComponentTypeId") != null) {
			returnVal.setCostComponentTypeId((String) fields.get("costComponentTypeId"));
}

		if(fields.get("costComponentCalcId") != null) {
			returnVal.setCostComponentCalcId((String) fields.get("costComponentCalcId"));
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
	public static WorkEffortCostCalc map(GenericValue val) {

WorkEffortCostCalc returnVal = new WorkEffortCostCalc();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setCostComponentTypeId(val.getString("costComponentTypeId"));
		returnVal.setCostComponentCalcId(val.getString("costComponentCalcId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static WorkEffortCostCalc map(HttpServletRequest request) throws Exception {

		WorkEffortCostCalc returnVal = new WorkEffortCostCalc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("costComponentTypeId"))  {
returnVal.setCostComponentTypeId(request.getParameter("costComponentTypeId"));
}
		if(paramMap.containsKey("costComponentCalcId"))  {
returnVal.setCostComponentCalcId(request.getParameter("costComponentCalcId"));
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
