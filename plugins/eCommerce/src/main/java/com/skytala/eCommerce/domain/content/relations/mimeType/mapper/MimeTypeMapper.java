package com.skytala.eCommerce.domain.content.relations.mimeType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.MimeType;

public class MimeTypeMapper  {


	public static Map<String, Object> map(MimeType mimetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(mimetype.getMimeTypeId() != null ){
			returnVal.put("mimeTypeId",mimetype.getMimeTypeId());
}

		if(mimetype.getDescription() != null ){
			returnVal.put("description",mimetype.getDescription());
}

		return returnVal;
}


	public static MimeType map(Map<String, Object> fields) {

		MimeType returnVal = new MimeType();

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static MimeType mapstrstr(Map<String, String> fields) throws Exception {

		MimeType returnVal = new MimeType();

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static MimeType map(GenericValue val) {

MimeType returnVal = new MimeType();
		returnVal.setMimeTypeId(val.getString("mimeTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static MimeType map(HttpServletRequest request) throws Exception {

		MimeType returnVal = new MimeType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("mimeTypeId")) {
returnVal.setMimeTypeId(request.getParameter("mimeTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
