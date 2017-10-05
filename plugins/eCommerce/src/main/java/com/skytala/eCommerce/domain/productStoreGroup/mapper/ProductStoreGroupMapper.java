package com.skytala.eCommerce.domain.productStoreGroup.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productStoreGroup.model.ProductStoreGroup;

public class ProductStoreGroupMapper  {


	public static Map<String, Object> map(ProductStoreGroup productstoregroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoregroup.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",productstoregroup.getProductStoreGroupId());
}

		if(productstoregroup.getProductStoreGroupTypeId() != null ){
			returnVal.put("productStoreGroupTypeId",productstoregroup.getProductStoreGroupTypeId());
}

		if(productstoregroup.getPrimaryParentGroupId() != null ){
			returnVal.put("primaryParentGroupId",productstoregroup.getPrimaryParentGroupId());
}

		if(productstoregroup.getProductStoreGroupName() != null ){
			returnVal.put("productStoreGroupName",productstoregroup.getProductStoreGroupName());
}

		if(productstoregroup.getDescription() != null ){
			returnVal.put("description",productstoregroup.getDescription());
}

		return returnVal;
}


	public static ProductStoreGroup map(Map<String, Object> fields) {

		ProductStoreGroup returnVal = new ProductStoreGroup();

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("productStoreGroupTypeId") != null) {
			returnVal.setProductStoreGroupTypeId((String) fields.get("productStoreGroupTypeId"));
}

		if(fields.get("primaryParentGroupId") != null) {
			returnVal.setPrimaryParentGroupId((String) fields.get("primaryParentGroupId"));
}

		if(fields.get("productStoreGroupName") != null) {
			returnVal.setProductStoreGroupName((String) fields.get("productStoreGroupName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductStoreGroup mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreGroup returnVal = new ProductStoreGroup();

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("productStoreGroupTypeId") != null) {
			returnVal.setProductStoreGroupTypeId((String) fields.get("productStoreGroupTypeId"));
}

		if(fields.get("primaryParentGroupId") != null) {
			returnVal.setPrimaryParentGroupId((String) fields.get("primaryParentGroupId"));
}

		if(fields.get("productStoreGroupName") != null) {
			returnVal.setProductStoreGroupName((String) fields.get("productStoreGroupName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductStoreGroup map(GenericValue val) {

ProductStoreGroup returnVal = new ProductStoreGroup();
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));
		returnVal.setProductStoreGroupTypeId(val.getString("productStoreGroupTypeId"));
		returnVal.setPrimaryParentGroupId(val.getString("primaryParentGroupId"));
		returnVal.setProductStoreGroupName(val.getString("productStoreGroupName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductStoreGroup map(HttpServletRequest request) throws Exception {

		ProductStoreGroup returnVal = new ProductStoreGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreGroupId")) {
returnVal.setProductStoreGroupId(request.getParameter("productStoreGroupId"));
}

		if(paramMap.containsKey("productStoreGroupTypeId"))  {
returnVal.setProductStoreGroupTypeId(request.getParameter("productStoreGroupTypeId"));
}
		if(paramMap.containsKey("primaryParentGroupId"))  {
returnVal.setPrimaryParentGroupId(request.getParameter("primaryParentGroupId"));
}
		if(paramMap.containsKey("productStoreGroupName"))  {
returnVal.setProductStoreGroupName(request.getParameter("productStoreGroupName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
