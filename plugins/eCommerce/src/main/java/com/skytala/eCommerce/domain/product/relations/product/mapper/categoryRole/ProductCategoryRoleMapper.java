package com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRole;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRole.ProductCategoryRole;

public class ProductCategoryRoleMapper  {


	public static Map<String, Object> map(ProductCategoryRole productcategoryrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategoryrole.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productcategoryrole.getProductCategoryId());
}

		if(productcategoryrole.getPartyId() != null ){
			returnVal.put("partyId",productcategoryrole.getPartyId());
}

		if(productcategoryrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",productcategoryrole.getRoleTypeId());
}

		if(productcategoryrole.getFromDate() != null ){
			returnVal.put("fromDate",productcategoryrole.getFromDate());
}

		if(productcategoryrole.getThruDate() != null ){
			returnVal.put("thruDate",productcategoryrole.getThruDate());
}

		if(productcategoryrole.getComments() != null ){
			returnVal.put("comments",productcategoryrole.getComments());
}

		return returnVal;
}


	public static ProductCategoryRole map(Map<String, Object> fields) {

		ProductCategoryRole returnVal = new ProductCategoryRole();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ProductCategoryRole mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryRole returnVal = new ProductCategoryRole();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static ProductCategoryRole map(GenericValue val) {

ProductCategoryRole returnVal = new ProductCategoryRole();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static ProductCategoryRole map(HttpServletRequest request) throws Exception {

		ProductCategoryRole returnVal = new ProductCategoryRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
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
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
