package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;

public class ContentSearchConstraintMapper  {


	public static Map<String, Object> map(ContentSearchConstraint contentsearchconstraint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentsearchconstraint.getContentSearchResultId() != null ){
			returnVal.put("contentSearchResultId",contentsearchconstraint.getContentSearchResultId());
}

		if(contentsearchconstraint.getConstraintSeqId() != null ){
			returnVal.put("constraintSeqId",contentsearchconstraint.getConstraintSeqId());
}

		if(contentsearchconstraint.getConstraintName() != null ){
			returnVal.put("constraintName",contentsearchconstraint.getConstraintName());
}

		if(contentsearchconstraint.getInfoString() != null ){
			returnVal.put("infoString",contentsearchconstraint.getInfoString());
}

		if(contentsearchconstraint.getIncludeSubCategories() != null ){
			returnVal.put("includeSubCategories",contentsearchconstraint.getIncludeSubCategories());
}

		if(contentsearchconstraint.getIsAnd() != null ){
			returnVal.put("isAnd",contentsearchconstraint.getIsAnd());
}

		if(contentsearchconstraint.getAnyPrefix() != null ){
			returnVal.put("anyPrefix",contentsearchconstraint.getAnyPrefix());
}

		if(contentsearchconstraint.getAnySuffix() != null ){
			returnVal.put("anySuffix",contentsearchconstraint.getAnySuffix());
}

		if(contentsearchconstraint.getRemoveStems() != null ){
			returnVal.put("removeStems",contentsearchconstraint.getRemoveStems());
}

		if(contentsearchconstraint.getLowValue() != null ){
			returnVal.put("lowValue",contentsearchconstraint.getLowValue());
}

		if(contentsearchconstraint.getHighValue() != null ){
			returnVal.put("highValue",contentsearchconstraint.getHighValue());
}

		return returnVal;
}


	public static ContentSearchConstraint map(Map<String, Object> fields) {

		ContentSearchConstraint returnVal = new ContentSearchConstraint();

		if(fields.get("contentSearchResultId") != null) {
			returnVal.setContentSearchResultId((String) fields.get("contentSearchResultId"));
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

		if(fields.get("includeSubCategories") != null) {
			returnVal.setIncludeSubCategories((boolean) fields.get("includeSubCategories"));
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
	public static ContentSearchConstraint mapstrstr(Map<String, String> fields) throws Exception {

		ContentSearchConstraint returnVal = new ContentSearchConstraint();

		if(fields.get("contentSearchResultId") != null) {
			returnVal.setContentSearchResultId((String) fields.get("contentSearchResultId"));
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

		if(fields.get("includeSubCategories") != null) {
String buf;
buf = fields.get("includeSubCategories");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeSubCategories(ibuf);
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
	public static ContentSearchConstraint map(GenericValue val) {

ContentSearchConstraint returnVal = new ContentSearchConstraint();
		returnVal.setContentSearchResultId(val.getString("contentSearchResultId"));
		returnVal.setConstraintSeqId(val.getString("constraintSeqId"));
		returnVal.setConstraintName(val.getString("constraintName"));
		returnVal.setInfoString(val.getString("infoString"));
		returnVal.setIncludeSubCategories(val.getBoolean("includeSubCategories"));
		returnVal.setIsAnd(val.getBoolean("isAnd"));
		returnVal.setAnyPrefix(val.getBoolean("anyPrefix"));
		returnVal.setAnySuffix(val.getBoolean("anySuffix"));
		returnVal.setRemoveStems(val.getBoolean("removeStems"));
		returnVal.setLowValue(val.getString("lowValue"));
		returnVal.setHighValue(val.getString("highValue"));


return returnVal;

}

public static ContentSearchConstraint map(HttpServletRequest request) throws Exception {

		ContentSearchConstraint returnVal = new ContentSearchConstraint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentSearchResultId")) {
returnVal.setContentSearchResultId(request.getParameter("contentSearchResultId"));
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
		if(paramMap.containsKey("includeSubCategories"))  {
String buf = request.getParameter("includeSubCategories");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeSubCategories(ibuf);
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
