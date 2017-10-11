package com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.supplierPrefOrder.model.SupplierPrefOrder;

public class SupplierPrefOrderMapper  {


	public static Map<String, Object> map(SupplierPrefOrder supplierpreforder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(supplierpreforder.getSupplierPrefOrderId() != null ){
			returnVal.put("supplierPrefOrderId",supplierpreforder.getSupplierPrefOrderId());
}

		if(supplierpreforder.getDescription() != null ){
			returnVal.put("description",supplierpreforder.getDescription());
}

		return returnVal;
}


	public static SupplierPrefOrder map(Map<String, Object> fields) {

		SupplierPrefOrder returnVal = new SupplierPrefOrder();

		if(fields.get("supplierPrefOrderId") != null) {
			returnVal.setSupplierPrefOrderId((String) fields.get("supplierPrefOrderId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SupplierPrefOrder mapstrstr(Map<String, String> fields) throws Exception {

		SupplierPrefOrder returnVal = new SupplierPrefOrder();

		if(fields.get("supplierPrefOrderId") != null) {
			returnVal.setSupplierPrefOrderId((String) fields.get("supplierPrefOrderId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SupplierPrefOrder map(GenericValue val) {

SupplierPrefOrder returnVal = new SupplierPrefOrder();
		returnVal.setSupplierPrefOrderId(val.getString("supplierPrefOrderId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SupplierPrefOrder map(HttpServletRequest request) throws Exception {

		SupplierPrefOrder returnVal = new SupplierPrefOrder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("supplierPrefOrderId")) {
returnVal.setSupplierPrefOrderId(request.getParameter("supplierPrefOrderId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
