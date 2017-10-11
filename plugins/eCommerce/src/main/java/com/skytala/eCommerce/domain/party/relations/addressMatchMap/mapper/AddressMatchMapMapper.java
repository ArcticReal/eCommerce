package com.skytala.eCommerce.domain.party.relations.addressMatchMap.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;

public class AddressMatchMapMapper  {


	public static Map<String, Object> map(AddressMatchMap addressmatchmap) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(addressmatchmap.getMapKey() != null ){
			returnVal.put("mapKey",addressmatchmap.getMapKey());
}

		if(addressmatchmap.getMapValue() != null ){
			returnVal.put("mapValue",addressmatchmap.getMapValue());
}

		if(addressmatchmap.getSequenceNum() != null ){
			returnVal.put("sequenceNum",addressmatchmap.getSequenceNum());
}

		return returnVal;
}


	public static AddressMatchMap map(Map<String, Object> fields) {

		AddressMatchMap returnVal = new AddressMatchMap();

		if(fields.get("mapKey") != null) {
			returnVal.setMapKey((String) fields.get("mapKey"));
}

		if(fields.get("mapValue") != null) {
			returnVal.setMapValue((String) fields.get("mapValue"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static AddressMatchMap mapstrstr(Map<String, String> fields) throws Exception {

		AddressMatchMap returnVal = new AddressMatchMap();

		if(fields.get("mapKey") != null) {
			returnVal.setMapKey((String) fields.get("mapKey"));
}

		if(fields.get("mapValue") != null) {
			returnVal.setMapValue((String) fields.get("mapValue"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static AddressMatchMap map(GenericValue val) {

AddressMatchMap returnVal = new AddressMatchMap();
		returnVal.setMapKey(val.getString("mapKey"));
		returnVal.setMapValue(val.getString("mapValue"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static AddressMatchMap map(HttpServletRequest request) throws Exception {

		AddressMatchMap returnVal = new AddressMatchMap();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("mapKey")) {
returnVal.setMapKey(request.getParameter("mapKey"));
}

		if(paramMap.containsKey("mapValue"))  {
returnVal.setMapValue(request.getParameter("mapValue"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
