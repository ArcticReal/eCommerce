package com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRole;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;

public class ProductStoreGroupRoleMapper  {


	public static Map<String, Object> map(ProductStoreGroupRole productstoregrouprole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoregrouprole.getProductStoreGroupId() != null ){
			returnVal.put("productStoreGroupId",productstoregrouprole.getProductStoreGroupId());
}

		if(productstoregrouprole.getPartyId() != null ){
			returnVal.put("partyId",productstoregrouprole.getPartyId());
}

		if(productstoregrouprole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",productstoregrouprole.getRoleTypeId());
}

		return returnVal;
}


	public static ProductStoreGroupRole map(Map<String, Object> fields) {

		ProductStoreGroupRole returnVal = new ProductStoreGroupRole();

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static ProductStoreGroupRole mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreGroupRole returnVal = new ProductStoreGroupRole();

		if(fields.get("productStoreGroupId") != null) {
			returnVal.setProductStoreGroupId((String) fields.get("productStoreGroupId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static ProductStoreGroupRole map(GenericValue val) {

ProductStoreGroupRole returnVal = new ProductStoreGroupRole();
		returnVal.setProductStoreGroupId(val.getString("productStoreGroupId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static ProductStoreGroupRole map(HttpServletRequest request) throws Exception {

		ProductStoreGroupRole returnVal = new ProductStoreGroupRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreGroupId")) {
returnVal.setProductStoreGroupId(request.getParameter("productStoreGroupId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
