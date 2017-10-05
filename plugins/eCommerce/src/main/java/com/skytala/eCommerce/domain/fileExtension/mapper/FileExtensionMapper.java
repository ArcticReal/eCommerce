package com.skytala.eCommerce.domain.fileExtension.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.fileExtension.model.FileExtension;

public class FileExtensionMapper  {


	public static Map<String, Object> map(FileExtension fileextension) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fileextension.getFileExtensionId() != null ){
			returnVal.put("fileExtensionId",fileextension.getFileExtensionId());
}

		if(fileextension.getMimeTypeId() != null ){
			returnVal.put("mimeTypeId",fileextension.getMimeTypeId());
}

		return returnVal;
}


	public static FileExtension map(Map<String, Object> fields) {

		FileExtension returnVal = new FileExtension();

		if(fields.get("fileExtensionId") != null) {
			returnVal.setFileExtensionId((String) fields.get("fileExtensionId"));
}

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}


		return returnVal;
 } 
	public static FileExtension mapstrstr(Map<String, String> fields) throws Exception {

		FileExtension returnVal = new FileExtension();

		if(fields.get("fileExtensionId") != null) {
			returnVal.setFileExtensionId((String) fields.get("fileExtensionId"));
}

		if(fields.get("mimeTypeId") != null) {
			returnVal.setMimeTypeId((String) fields.get("mimeTypeId"));
}


		return returnVal;
 } 
	public static FileExtension map(GenericValue val) {

FileExtension returnVal = new FileExtension();
		returnVal.setFileExtensionId(val.getString("fileExtensionId"));
		returnVal.setMimeTypeId(val.getString("mimeTypeId"));


return returnVal;

}

public static FileExtension map(HttpServletRequest request) throws Exception {

		FileExtension returnVal = new FileExtension();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fileExtensionId")) {
returnVal.setFileExtensionId(request.getParameter("fileExtensionId"));
}

		if(paramMap.containsKey("mimeTypeId"))  {
returnVal.setMimeTypeId(request.getParameter("mimeTypeId"));
}
return returnVal;

}
}
