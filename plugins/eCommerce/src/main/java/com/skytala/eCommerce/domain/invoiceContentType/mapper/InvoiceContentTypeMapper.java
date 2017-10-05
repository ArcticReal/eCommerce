package com.skytala.eCommerce.domain.invoiceContentType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.invoiceContentType.model.InvoiceContentType;

public class InvoiceContentTypeMapper  {


	public static Map<String, Object> map(InvoiceContentType invoicecontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoicecontenttype.getInvoiceContentTypeId() != null ){
			returnVal.put("invoiceContentTypeId",invoicecontenttype.getInvoiceContentTypeId());
}

		if(invoicecontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",invoicecontenttype.getParentTypeId());
}

		if(invoicecontenttype.getHasTable() != null ){
			returnVal.put("hasTable",invoicecontenttype.getHasTable());
}

		if(invoicecontenttype.getDescription() != null ){
			returnVal.put("description",invoicecontenttype.getDescription());
}

		return returnVal;
}


	public static InvoiceContentType map(Map<String, Object> fields) {

		InvoiceContentType returnVal = new InvoiceContentType();

		if(fields.get("invoiceContentTypeId") != null) {
			returnVal.setInvoiceContentTypeId((String) fields.get("invoiceContentTypeId"));
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
	public static InvoiceContentType mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceContentType returnVal = new InvoiceContentType();

		if(fields.get("invoiceContentTypeId") != null) {
			returnVal.setInvoiceContentTypeId((String) fields.get("invoiceContentTypeId"));
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
	public static InvoiceContentType map(GenericValue val) {

InvoiceContentType returnVal = new InvoiceContentType();
		returnVal.setInvoiceContentTypeId(val.getString("invoiceContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InvoiceContentType map(HttpServletRequest request) throws Exception {

		InvoiceContentType returnVal = new InvoiceContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceContentTypeId")) {
returnVal.setInvoiceContentTypeId(request.getParameter("invoiceContentTypeId"));
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
