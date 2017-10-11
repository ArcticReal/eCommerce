package com.skytala.eCommerce.domain.product.relations.quantityBreak.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.QuantityBreak;

public class QuantityBreakMapper  {


	public static Map<String, Object> map(QuantityBreak quantitybreak) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quantitybreak.getQuantityBreakId() != null ){
			returnVal.put("quantityBreakId",quantitybreak.getQuantityBreakId());
}

		if(quantitybreak.getQuantityBreakTypeId() != null ){
			returnVal.put("quantityBreakTypeId",quantitybreak.getQuantityBreakTypeId());
}

		if(quantitybreak.getFromQuantity() != null ){
			returnVal.put("fromQuantity",quantitybreak.getFromQuantity());
}

		if(quantitybreak.getThruQuantity() != null ){
			returnVal.put("thruQuantity",quantitybreak.getThruQuantity());
}

		return returnVal;
}


	public static QuantityBreak map(Map<String, Object> fields) {

		QuantityBreak returnVal = new QuantityBreak();

		if(fields.get("quantityBreakId") != null) {
			returnVal.setQuantityBreakId((String) fields.get("quantityBreakId"));
}

		if(fields.get("quantityBreakTypeId") != null) {
			returnVal.setQuantityBreakTypeId((String) fields.get("quantityBreakTypeId"));
}

		if(fields.get("fromQuantity") != null) {
			returnVal.setFromQuantity((BigDecimal) fields.get("fromQuantity"));
}

		if(fields.get("thruQuantity") != null) {
			returnVal.setThruQuantity((BigDecimal) fields.get("thruQuantity"));
}


		return returnVal;
 } 
	public static QuantityBreak mapstrstr(Map<String, String> fields) throws Exception {

		QuantityBreak returnVal = new QuantityBreak();

		if(fields.get("quantityBreakId") != null) {
			returnVal.setQuantityBreakId((String) fields.get("quantityBreakId"));
}

		if(fields.get("quantityBreakTypeId") != null) {
			returnVal.setQuantityBreakTypeId((String) fields.get("quantityBreakTypeId"));
}

		if(fields.get("fromQuantity") != null) {
String buf;
buf = fields.get("fromQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFromQuantity(bd);
}

		if(fields.get("thruQuantity") != null) {
String buf;
buf = fields.get("thruQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setThruQuantity(bd);
}


		return returnVal;
 } 
	public static QuantityBreak map(GenericValue val) {

QuantityBreak returnVal = new QuantityBreak();
		returnVal.setQuantityBreakId(val.getString("quantityBreakId"));
		returnVal.setQuantityBreakTypeId(val.getString("quantityBreakTypeId"));
		returnVal.setFromQuantity(val.getBigDecimal("fromQuantity"));
		returnVal.setThruQuantity(val.getBigDecimal("thruQuantity"));


return returnVal;

}

public static QuantityBreak map(HttpServletRequest request) throws Exception {

		QuantityBreak returnVal = new QuantityBreak();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quantityBreakId")) {
returnVal.setQuantityBreakId(request.getParameter("quantityBreakId"));
}

		if(paramMap.containsKey("quantityBreakTypeId"))  {
returnVal.setQuantityBreakTypeId(request.getParameter("quantityBreakTypeId"));
}
		if(paramMap.containsKey("fromQuantity"))  {
String buf = request.getParameter("fromQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFromQuantity(bd);
}
		if(paramMap.containsKey("thruQuantity"))  {
String buf = request.getParameter("thruQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setThruQuantity(bd);
}
return returnVal;

}
}
