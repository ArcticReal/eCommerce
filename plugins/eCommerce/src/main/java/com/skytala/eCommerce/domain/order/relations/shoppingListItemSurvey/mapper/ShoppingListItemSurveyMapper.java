package com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.shoppingListItemSurvey.model.ShoppingListItemSurvey;

public class ShoppingListItemSurveyMapper  {


	public static Map<String, Object> map(ShoppingListItemSurvey shoppinglistitemsurvey) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shoppinglistitemsurvey.getShoppingListId() != null ){
			returnVal.put("shoppingListId",shoppinglistitemsurvey.getShoppingListId());
}

		if(shoppinglistitemsurvey.getShoppingListItemSeqId() != null ){
			returnVal.put("shoppingListItemSeqId",shoppinglistitemsurvey.getShoppingListItemSeqId());
}

		if(shoppinglistitemsurvey.getSurveyResponseId() != null ){
			returnVal.put("surveyResponseId",shoppinglistitemsurvey.getSurveyResponseId());
}

		return returnVal;
}


	public static ShoppingListItemSurvey map(Map<String, Object> fields) {

		ShoppingListItemSurvey returnVal = new ShoppingListItemSurvey();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("shoppingListItemSeqId") != null) {
			returnVal.setShoppingListItemSeqId((String) fields.get("shoppingListItemSeqId"));
}

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}


		return returnVal;
 } 
	public static ShoppingListItemSurvey mapstrstr(Map<String, String> fields) throws Exception {

		ShoppingListItemSurvey returnVal = new ShoppingListItemSurvey();

		if(fields.get("shoppingListId") != null) {
			returnVal.setShoppingListId((String) fields.get("shoppingListId"));
}

		if(fields.get("shoppingListItemSeqId") != null) {
			returnVal.setShoppingListItemSeqId((String) fields.get("shoppingListItemSeqId"));
}

		if(fields.get("surveyResponseId") != null) {
			returnVal.setSurveyResponseId((String) fields.get("surveyResponseId"));
}


		return returnVal;
 } 
	public static ShoppingListItemSurvey map(GenericValue val) {

ShoppingListItemSurvey returnVal = new ShoppingListItemSurvey();
		returnVal.setShoppingListId(val.getString("shoppingListId"));
		returnVal.setShoppingListItemSeqId(val.getString("shoppingListItemSeqId"));
		returnVal.setSurveyResponseId(val.getString("surveyResponseId"));


return returnVal;

}

public static ShoppingListItemSurvey map(HttpServletRequest request) throws Exception {

		ShoppingListItemSurvey returnVal = new ShoppingListItemSurvey();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shoppingListId")) {
returnVal.setShoppingListId(request.getParameter("shoppingListId"));
}

		if(paramMap.containsKey("shoppingListItemSeqId"))  {
returnVal.setShoppingListItemSeqId(request.getParameter("shoppingListItemSeqId"));
}
		if(paramMap.containsKey("surveyResponseId"))  {
returnVal.setSurveyResponseId(request.getParameter("surveyResponseId"));
}
return returnVal;

}
}
