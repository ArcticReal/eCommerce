package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.searchConstraint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;

public class WorkEffortSearchConstraintMapper  {


	public static Map<String, Object> map(WorkEffortSearchConstraint workeffortsearchconstraint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortsearchconstraint.getWorkEffortSearchResultId() != null ){
			returnVal.put("workEffortSearchResultId",workeffortsearchconstraint.getWorkEffortSearchResultId());
}

		if(workeffortsearchconstraint.getConstraintSeqId() != null ){
			returnVal.put("constraintSeqId",workeffortsearchconstraint.getConstraintSeqId());
}

		if(workeffortsearchconstraint.getConstraintName() != null ){
			returnVal.put("constraintName",workeffortsearchconstraint.getConstraintName());
}

		if(workeffortsearchconstraint.getInfoString() != null ){
			returnVal.put("infoString",workeffortsearchconstraint.getInfoString());
}

		if(workeffortsearchconstraint.getIncludeSubWorkEfforts() != null ){
			returnVal.put("includeSubWorkEfforts",workeffortsearchconstraint.getIncludeSubWorkEfforts());
}

		if(workeffortsearchconstraint.getIsAnd() != null ){
			returnVal.put("isAnd",workeffortsearchconstraint.getIsAnd());
}

		if(workeffortsearchconstraint.getAnyPrefix() != null ){
			returnVal.put("anyPrefix",workeffortsearchconstraint.getAnyPrefix());
}

		if(workeffortsearchconstraint.getAnySuffix() != null ){
			returnVal.put("anySuffix",workeffortsearchconstraint.getAnySuffix());
}

		if(workeffortsearchconstraint.getRemoveStems() != null ){
			returnVal.put("removeStems",workeffortsearchconstraint.getRemoveStems());
}

		if(workeffortsearchconstraint.getLowValue() != null ){
			returnVal.put("lowValue",workeffortsearchconstraint.getLowValue());
}

		if(workeffortsearchconstraint.getHighValue() != null ){
			returnVal.put("highValue",workeffortsearchconstraint.getHighValue());
}

		return returnVal;
}


	public static WorkEffortSearchConstraint map(Map<String, Object> fields) {

		WorkEffortSearchConstraint returnVal = new WorkEffortSearchConstraint();

		if(fields.get("workEffortSearchResultId") != null) {
			returnVal.setWorkEffortSearchResultId((String) fields.get("workEffortSearchResultId"));
}

		if(fields.get("constraintSeqId") != null) {
			returnVal.setConstraintSeqId((String) fields.get("constraintSeqId"));
}

		if(fields.get("constraintName") != null) {
			returnVal.setConstraintName((String) fields.get("constraintName"));
}

		if(fields.get("infoString") != null) {
			returnVal.setInfoString((String) fields.get("infoString"));
}

		if(fields.get("includeSubWorkEfforts") != null) {
			returnVal.setIncludeSubWorkEfforts((boolean) fields.get("includeSubWorkEfforts"));
}

		if(fields.get("isAnd") != null) {
			returnVal.setIsAnd((boolean) fields.get("isAnd"));
}

		if(fields.get("anyPrefix") != null) {
			returnVal.setAnyPrefix((boolean) fields.get("anyPrefix"));
}

		if(fields.get("anySuffix") != null) {
			returnVal.setAnySuffix((boolean) fields.get("anySuffix"));
}

		if(fields.get("removeStems") != null) {
			returnVal.setRemoveStems((boolean) fields.get("removeStems"));
}

		if(fields.get("lowValue") != null) {
			returnVal.setLowValue((String) fields.get("lowValue"));
}

		if(fields.get("highValue") != null) {
			returnVal.setHighValue((String) fields.get("highValue"));
}


		return returnVal;
 } 
	public static WorkEffortSearchConstraint mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortSearchConstraint returnVal = new WorkEffortSearchConstraint();

		if(fields.get("workEffortSearchResultId") != null) {
			returnVal.setWorkEffortSearchResultId((String) fields.get("workEffortSearchResultId"));
}

		if(fields.get("constraintSeqId") != null) {
			returnVal.setConstraintSeqId((String) fields.get("constraintSeqId"));
}

		if(fields.get("constraintName") != null) {
			returnVal.setConstraintName((String) fields.get("constraintName"));
}

		if(fields.get("infoString") != null) {
			returnVal.setInfoString((String) fields.get("infoString"));
}

		if(fields.get("includeSubWorkEfforts") != null) {
String buf;
buf = fields.get("includeSubWorkEfforts");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeSubWorkEfforts(ibuf);
}

		if(fields.get("isAnd") != null) {
String buf;
buf = fields.get("isAnd");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsAnd(ibuf);
}

		if(fields.get("anyPrefix") != null) {
String buf;
buf = fields.get("anyPrefix");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAnyPrefix(ibuf);
}

		if(fields.get("anySuffix") != null) {
String buf;
buf = fields.get("anySuffix");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAnySuffix(ibuf);
}

		if(fields.get("removeStems") != null) {
String buf;
buf = fields.get("removeStems");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRemoveStems(ibuf);
}

		if(fields.get("lowValue") != null) {
			returnVal.setLowValue((String) fields.get("lowValue"));
}

		if(fields.get("highValue") != null) {
			returnVal.setHighValue((String) fields.get("highValue"));
}


		return returnVal;
 } 
	public static WorkEffortSearchConstraint map(GenericValue val) {

WorkEffortSearchConstraint returnVal = new WorkEffortSearchConstraint();
		returnVal.setWorkEffortSearchResultId(val.getString("workEffortSearchResultId"));
		returnVal.setConstraintSeqId(val.getString("constraintSeqId"));
		returnVal.setConstraintName(val.getString("constraintName"));
		returnVal.setInfoString(val.getString("infoString"));
		returnVal.setIncludeSubWorkEfforts(val.getBoolean("includeSubWorkEfforts"));
		returnVal.setIsAnd(val.getBoolean("isAnd"));
		returnVal.setAnyPrefix(val.getBoolean("anyPrefix"));
		returnVal.setAnySuffix(val.getBoolean("anySuffix"));
		returnVal.setRemoveStems(val.getBoolean("removeStems"));
		returnVal.setLowValue(val.getString("lowValue"));
		returnVal.setHighValue(val.getString("highValue"));


return returnVal;

}

public static WorkEffortSearchConstraint map(HttpServletRequest request) throws Exception {

		WorkEffortSearchConstraint returnVal = new WorkEffortSearchConstraint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortSearchResultId")) {
returnVal.setWorkEffortSearchResultId(request.getParameter("workEffortSearchResultId"));
}

		if(paramMap.containsKey("constraintSeqId"))  {
returnVal.setConstraintSeqId(request.getParameter("constraintSeqId"));
}
		if(paramMap.containsKey("constraintName"))  {
returnVal.setConstraintName(request.getParameter("constraintName"));
}
		if(paramMap.containsKey("infoString"))  {
returnVal.setInfoString(request.getParameter("infoString"));
}
		if(paramMap.containsKey("includeSubWorkEfforts"))  {
String buf = request.getParameter("includeSubWorkEfforts");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeSubWorkEfforts(ibuf);
}
		if(paramMap.containsKey("isAnd"))  {
String buf = request.getParameter("isAnd");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsAnd(ibuf);
}
		if(paramMap.containsKey("anyPrefix"))  {
String buf = request.getParameter("anyPrefix");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAnyPrefix(ibuf);
}
		if(paramMap.containsKey("anySuffix"))  {
String buf = request.getParameter("anySuffix");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAnySuffix(ibuf);
}
		if(paramMap.containsKey("removeStems"))  {
String buf = request.getParameter("removeStems");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRemoveStems(ibuf);
}
		if(paramMap.containsKey("lowValue"))  {
returnVal.setLowValue(request.getParameter("lowValue"));
}
		if(paramMap.containsKey("highValue"))  {
returnVal.setHighValue(request.getParameter("highValue"));
}
return returnVal;

}
}
