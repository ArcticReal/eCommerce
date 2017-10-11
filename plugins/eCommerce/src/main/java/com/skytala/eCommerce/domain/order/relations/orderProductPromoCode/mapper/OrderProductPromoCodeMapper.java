package com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderProductPromoCode.model.OrderProductPromoCode;

public class OrderProductPromoCodeMapper  {


	public static Map<String, Object> map(OrderProductPromoCode orderproductpromocode) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderproductpromocode.getOrderId() != null ){
			returnVal.put("orderId",orderproductpromocode.getOrderId());
}

		if(orderproductpromocode.getProductPromoCodeId() != null ){
			returnVal.put("productPromoCodeId",orderproductpromocode.getProductPromoCodeId());
}

		return returnVal;
}


	public static OrderProductPromoCode map(Map<String, Object> fields) {

		OrderProductPromoCode returnVal = new OrderProductPromoCode();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}


		return returnVal;
 } 
	public static OrderProductPromoCode mapstrstr(Map<String, String> fields) throws Exception {

		OrderProductPromoCode returnVal = new OrderProductPromoCode();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}


		return returnVal;
 } 
	public static OrderProductPromoCode map(GenericValue val) {

OrderProductPromoCode returnVal = new OrderProductPromoCode();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setProductPromoCodeId(val.getString("productPromoCodeId"));


return returnVal;

}

public static OrderProductPromoCode map(HttpServletRequest request) throws Exception {

		OrderProductPromoCode returnVal = new OrderProductPromoCode();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("productPromoCodeId"))  {
returnVal.setProductPromoCodeId(request.getParameter("productPromoCodeId"));
}
return returnVal;

}
}
