package com.skytala.eCommerce.domain.order.relations.shoppingList.mapper.workEffort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingList.model.workEffort.ShoppingListWorkEffort;

public class ShoppingListWorkEffortMapper  {


	public static Map<String, Object> map(ShoppingListWorkEffort shoppinglistworkeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shoppinglistworkeffort.getShoppingListId() != null ){
			returnVal.put("shoppingListId",shoppinglistworkeffort.getShoppingListId());
}

		if(shoppinglistworkeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",shoppinglistworkeffort.getWorkEffortId());
}

		return returnVal;
}


	public static ShoppingListWorkEffort map(Map<String, Object> fields) {

		ShoppingListWorkEffort returnVal = new ShoppingListWorkEffort();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static ShoppingListWorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		ShoppingListWorkEffort returnVal = new ShoppingListWorkEffort();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static ShoppingListWorkEffort map(GenericValue val) {

ShoppingListWorkEffort returnVal = new ShoppingListWorkEffort();
		returnVal.setShoppingListId(val.getString("shoppingListId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static ShoppingListWorkEffort map(HttpServletRequest request) throws Exception {

		ShoppingListWorkEffort returnVal = new ShoppingListWorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shoppingListId")) {
returnVal.setShoppingListId(request.getParameter("shoppingListId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
