package com.skytala.eCommerce.domain.order.relations.orderContent.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;

public class OrderContentTypeMapper  {


	public static Map<String, Object> map(OrderContentType ordercontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordercontenttype.getOrderContentTypeId() != null ){
			returnVal.put("orderContentTypeId",ordercontenttype.getOrderContentTypeId());
}

		if(ordercontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",ordercontenttype.getParentTypeId());
}

		if(ordercontenttype.getHasTable() != null ){
			returnVal.put("hasTable",ordercontenttype.getHasTable());
}

		if(ordercontenttype.getDescription() != null ){
			returnVal.put("description",ordercontenttype.getDescription());
}

		return returnVal;
}


	public static OrderContentType map(Map<String, Object> fields) {

		OrderContentType returnVal = new OrderContentType();

		if(fields.get("orderContentTypeId") != null) {
			returnVal.setOrderContentTypeId((String) fields.get("orderContentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderContentType mapstrstr(Map<String, String> fields) throws Exception {

		OrderContentType returnVal = new OrderContentType();

		if(fields.get("orderContentTypeId") != null) {
			returnVal.setOrderContentTypeId((String) fields.get("orderContentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static OrderContentType map(GenericValue val) {

OrderContentType returnVal = new OrderContentType();
		returnVal.setOrderContentTypeId(val.getString("orderContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static OrderContentType map(HttpServletRequest request) throws Exception {

		OrderContentType returnVal = new OrderContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderContentTypeId")) {
returnVal.setOrderContentTypeId(request.getParameter("orderContentTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
