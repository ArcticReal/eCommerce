package com.skytala.eCommerce.domain.supplierRatingType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;

public class SupplierRatingTypeMapper  {


	public static Map<String, Object> map(SupplierRatingType supplierratingtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(supplierratingtype.getSupplierRatingTypeId() != null ){
			returnVal.put("supplierRatingTypeId",supplierratingtype.getSupplierRatingTypeId());
}

		if(supplierratingtype.getDescription() != null ){
			returnVal.put("description",supplierratingtype.getDescription());
}

		return returnVal;
}


	public static SupplierRatingType map(Map<String, Object> fields) {

		SupplierRatingType returnVal = new SupplierRatingType();

		if(fields.get("supplierRatingTypeId") != null) {
			returnVal.setSupplierRatingTypeId((String) fields.get("supplierRatingTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SupplierRatingType mapstrstr(Map<String, String> fields) throws Exception {

		SupplierRatingType returnVal = new SupplierRatingType();

		if(fields.get("supplierRatingTypeId") != null) {
			returnVal.setSupplierRatingTypeId((String) fields.get("supplierRatingTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SupplierRatingType map(GenericValue val) {

SupplierRatingType returnVal = new SupplierRatingType();
		returnVal.setSupplierRatingTypeId(val.getString("supplierRatingTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SupplierRatingType map(HttpServletRequest request) throws Exception {

		SupplierRatingType returnVal = new SupplierRatingType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("supplierRatingTypeId")) {
returnVal.setSupplierRatingTypeId(request.getParameter("supplierRatingTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
