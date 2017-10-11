package com.skytala.eCommerce.domain.order.relations.shoppingListType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingListType.model.ShoppingListType;

public class ShoppingListTypeMapper  {


	public static Map<String, Object> map(ShoppingListType shoppinglisttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shoppinglisttype.getShoppingListTypeId() != null ){
			returnVal.put("shoppingListTypeId",shoppinglisttype.getShoppingListTypeId());
}

		if(shoppinglisttype.getDescription() != null ){
			returnVal.put("description",shoppinglisttype.getDescription());
}

		return returnVal;
}


	public static ShoppingListType map(Map<String, Object> fields) {

		ShoppingListType returnVal = new ShoppingListType();

		if(fields.get("shoppingListTypeId") != null) {
			returnVal.setShoppingListTypeId((String) fields.get("shoppingListTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShoppingListType mapstrstr(Map<String, String> fields) throws Exception {

		ShoppingListType returnVal = new ShoppingListType();

		if(fields.get("shoppingListTypeId") != null) {
			returnVal.setShoppingListTypeId((String) fields.get("shoppingListTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ShoppingListType map(GenericValue val) {

ShoppingListType returnVal = new ShoppingListType();
		returnVal.setShoppingListTypeId(val.getString("shoppingListTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ShoppingListType map(HttpServletRequest request) throws Exception {

		ShoppingListType returnVal = new ShoppingListType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shoppingListTypeId")) {
returnVal.setShoppingListTypeId(request.getParameter("shoppingListTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
