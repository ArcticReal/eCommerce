package com.skytala.eCommerce.domain.product.relations.lot.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;

public class LotMapper  {


	public static Map<String, Object> map(Lot lot) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(lot.getLotId() != null ){
			returnVal.put("lotId",lot.getLotId());
}

		if(lot.getCreationDate() != null ){
			returnVal.put("creationDate",lot.getCreationDate());
}

		if(lot.getQuantity() != null ){
			returnVal.put("quantity",lot.getQuantity());
}

		if(lot.getExpirationDate() != null ){
			returnVal.put("expirationDate",lot.getExpirationDate());
}

		return returnVal;
}


	public static Lot map(Map<String, Object> fields) {

		Lot returnVal = new Lot();

		if(fields.get("lotId") != null) {
			returnVal.setLotId((String) fields.get("lotId"));
}

		if(fields.get("creationDate") != null) {
			returnVal.setCreationDate((Timestamp) fields.get("creationDate"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("expirationDate") != null) {
			returnVal.setExpirationDate((Timestamp) fields.get("expirationDate"));
}


		return returnVal;
 } 
	public static Lot mapstrstr(Map<String, String> fields) throws Exception {

		Lot returnVal = new Lot();

		if(fields.get("lotId") != null) {
			returnVal.setLotId((String) fields.get("lotId"));
}

		if(fields.get("creationDate") != null) {
String buf = fields.get("creationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreationDate(ibuf);
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("expirationDate") != null) {
String buf = fields.get("expirationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpirationDate(ibuf);
}


		return returnVal;
 } 
	public static Lot map(GenericValue val) {

Lot returnVal = new Lot();
		returnVal.setLotId(val.getString("lotId"));
		returnVal.setCreationDate(val.getTimestamp("creationDate"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setExpirationDate(val.getTimestamp("expirationDate"));


return returnVal;

}

public static Lot map(HttpServletRequest request) throws Exception {

		Lot returnVal = new Lot();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("lotId")) {
returnVal.setLotId(request.getParameter("lotId"));
}

		if(paramMap.containsKey("creationDate"))  {
String buf = request.getParameter("creationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreationDate(ibuf);
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("expirationDate"))  {
String buf = request.getParameter("expirationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExpirationDate(ibuf);
}
return returnVal;

}
}
