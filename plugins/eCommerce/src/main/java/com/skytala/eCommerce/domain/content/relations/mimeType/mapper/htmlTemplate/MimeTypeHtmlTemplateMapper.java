package com.skytala.eCommerce.domain.content.relations.mimeType.mapper.htmlTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;

public class MimeTypeHtmlTemplateMapper  {


	public static Map<String, Object> map(MimeTypeHtmlTemplate mimetypehtmltemplate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(mimetypehtmltemplate.getMimeTypeId() != null ){
			returnVal.put("mimeTypeId",mimetypehtmltemplate.getMimeTypeId());
}

		if(mimetypehtmltemplate.getTemplateLocation() != null ){
			returnVal.put("templateLocation",mimetypehtmltemplate.getTemplateLocation());
}

		return returnVal;
}


	public static MimeTypeHtmlTemplate map(Map<String, Object> fields) {

		MimeTypeHtmlTemplate returnVal = new MimeTypeHtmlTemplate();

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}

		if(fields.get("templateLocation") != null) {
			returnVal.setTemplateLocation((String) fields.get("templateLocation"));
}


		return returnVal;
 } 
	public static MimeTypeHtmlTemplate mapstrstr(Map<String, String> fields) throws Exception {

		MimeTypeHtmlTemplate returnVal = new MimeTypeHtmlTemplate();

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}

		if(fields.get("templateLocation") != null) {
			returnVal.setTemplateLocation((String) fields.get("templateLocation"));
}


		return returnVal;
 } 
	public static MimeTypeHtmlTemplate map(GenericValue val) {

MimeTypeHtmlTemplate returnVal = new MimeTypeHtmlTemplate();
		returnVal.setMimeTypeId(val.getString("mimeTypeId"));
		returnVal.setTemplateLocation(val.getString("templateLocation"));


return returnVal;

}

public static MimeTypeHtmlTemplate map(HttpServletRequest request) throws Exception {

		MimeTypeHtmlTemplate returnVal = new MimeTypeHtmlTemplate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("mimeTypeId")) {
returnVal.setMimeTypeId(request.getParameter("mimeTypeId"));
}

		if(paramMap.containsKey("templateLocation"))  {
returnVal.setTemplateLocation(request.getParameter("templateLocation"));
}
return returnVal;

}
}
