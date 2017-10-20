package com.skytala.eCommerce.domain.product.relations.product.mapper.searchConstraint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.searchConstraint.ProductSearchConstraint;

public class ProductSearchConstraintMapper  {


	public static Map<String, Object> map(ProductSearchConstraint productsearchconstraint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productsearchconstraint.getProductSearchResultId() != null ){
			returnVal.put("productSearchResultId",productsearchconstraint.getProductSearchResultId());
}

		if(productsearchconstraint.getConstraintSeqId() != null ){
			returnVal.put("constraintSeqId",productsearchconstraint.getConstraintSeqId());
}

		if(productsearchconstraint.getConstraintName() != null ){
			returnVal.put("constraintName",productsearchconstraint.getConstraintName());
}

		if(productsearchconstraint.getInfoString() != null ){
			returnVal.put("infoString",productsearchconstraint.getInfoString());
}

		if(productsearchconstraint.getIncludeSubCategories() != null ){
			returnVal.put("includeSubCategories",productsearchconstraint.getIncludeSubCategories());
}

		if(productsearchconstraint.getIsAnd() != null ){
			returnVal.put("isAnd",productsearchconstraint.getIsAnd());
}

		if(productsearchconstraint.getAnyPrefix() != null ){
			returnVal.put("anyPrefix",productsearchconstraint.getAnyPrefix());
}

		if(productsearchconstraint.getAnySuffix() != null ){
			returnVal.put("anySuffix",productsearchconstraint.getAnySuffix());
}

		if(productsearchconstraint.getRemoveStems() != null ){
			returnVal.put("removeStems",productsearchconstraint.getRemoveStems());
}

		if(productsearchconstraint.getLowValue() != null ){
			returnVal.put("lowValue",productsearchconstraint.getLowValue());
}

		if(productsearchconstraint.getHighValue() != null ){
			returnVal.put("highValue",productsearchconstraint.getHighValue());
}

		return returnVal;
}


	public static ProductSearchConstraint map(Map<String, Object> fields) {

		ProductSearchConstraint returnVal = new ProductSearchConstraint();

		if(fields.get("productSearchResultId") != null) {
			returnVal.setProductSearchResultId((String) fields.get("productSearchResultId"));
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
	public static ProductSearchConstraint mapstrstr(Map<String, String> fields) throws Exception {

		ProductSearchConstraint returnVal = new ProductSearchConstraint();

		if(fields.get("productSearchResultId") != null) {
			returnVal.setProductSearchResultId((String) fields.get("productSearchResultId"));
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
	public static ProductSearchConstraint map(GenericValue val) {

ProductSearchConstraint returnVal = new ProductSearchConstraint();
		returnVal.setProductSearchResultId(val.getString("productSearchResultId"));
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

public static ProductSearchConstraint map(HttpServletRequest request) throws Exception {

		ProductSearchConstraint returnVal = new ProductSearchConstraint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productSearchResultId")) {
returnVal.setProductSearchResultId(request.getParameter("productSearchResultId"));
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
