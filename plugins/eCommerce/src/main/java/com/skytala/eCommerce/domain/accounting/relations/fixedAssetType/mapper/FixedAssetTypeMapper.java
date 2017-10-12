package com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetType.model.FixedAssetType;

public class FixedAssetTypeMapper  {


	public static Map<String, Object> map(FixedAssetType fixedassettype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassettype.getFixedAssetTypeId() != null ){
			returnVal.put("fixedAssetTypeId",fixedassettype.getFixedAssetTypeId());
}

		if(fixedassettype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",fixedassettype.getParentTypeId());
}

		if(fixedassettype.getHasTable() != null ){
			returnVal.put("hasTable",fixedassettype.getHasTable());
}

		if(fixedassettype.getDescription() != null ){
			returnVal.put("description",fixedassettype.getDescription());
}

		return returnVal;
}


	public static FixedAssetType map(Map<String, Object> fields) {

		FixedAssetType returnVal = new FixedAssetType();

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
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
	public static FixedAssetType mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetType returnVal = new FixedAssetType();

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
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
	public static FixedAssetType map(GenericValue val) {

FixedAssetType returnVal = new FixedAssetType();
		returnVal.setFixedAssetTypeId(val.getString("fixedAssetTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FixedAssetType map(HttpServletRequest request) throws Exception {

		FixedAssetType returnVal = new FixedAssetType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetTypeId")) {
returnVal.setFixedAssetTypeId(request.getParameter("fixedAssetTypeId"));
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
