package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;

public class CartAbandonedLineMapper  {


	public static Map<String, Object> map(CartAbandonedLine cartabandonedline) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(cartabandonedline.getVisitId() != null ){
			returnVal.put("visitId",cartabandonedline.getVisitId());
}

		if(cartabandonedline.getCartAbandonedLineSeqId() != null ){
			returnVal.put("cartAbandonedLineSeqId",cartabandonedline.getCartAbandonedLineSeqId());
}

		if(cartabandonedline.getProductId() != null ){
			returnVal.put("productId",cartabandonedline.getProductId());
}

		if(cartabandonedline.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",cartabandonedline.getProdCatalogId());
}

		if(cartabandonedline.getQuantity() != null ){
			returnVal.put("quantity",cartabandonedline.getQuantity());
}

		if(cartabandonedline.getReservStart() != null ){
			returnVal.put("reservStart",cartabandonedline.getReservStart());
}

		if(cartabandonedline.getReservLength() != null ){
			returnVal.put("reservLength",cartabandonedline.getReservLength());
}

		if(cartabandonedline.getReservPersons() != null ){
			returnVal.put("reservPersons",cartabandonedline.getReservPersons());
}

		if(cartabandonedline.getUnitPrice() != null ){
			returnVal.put("unitPrice",cartabandonedline.getUnitPrice());
}

		if(cartabandonedline.getReserv2ndPPPerc() != null ){
			returnVal.put("reserv2ndPPPerc",cartabandonedline.getReserv2ndPPPerc());
}

		if(cartabandonedline.getReservNthPPPerc() != null ){
			returnVal.put("reservNthPPPerc",cartabandonedline.getReservNthPPPerc());
}

		if(cartabandonedline.getConfigId() != null ){
			returnVal.put("configId",cartabandonedline.getConfigId());
}

		if(cartabandonedline.getTotalWithAdjustments() != null ){
			returnVal.put("totalWithAdjustments",cartabandonedline.getTotalWithAdjustments());
}

		if(cartabandonedline.getWasReserved() != null ){
			returnVal.put("wasReserved",cartabandonedline.getWasReserved());
}

		return returnVal;
}


	public static CartAbandonedLine map(Map<String, Object> fields) {

		CartAbandonedLine returnVal = new CartAbandonedLine();

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("cartAbandonedLineSeqId") != null) {
			returnVal.setCartAbandonedLineSeqId((String) fields.get("cartAbandonedLineSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("reservStart") != null) {
			returnVal.setReservStart((Timestamp) fields.get("reservStart"));
}

		if(fields.get("reservLength") != null) {
			returnVal.setReservLength((BigDecimal) fields.get("reservLength"));
}

		if(fields.get("reservPersons") != null) {
			returnVal.setReservPersons((BigDecimal) fields.get("reservPersons"));
}

		if(fields.get("unitPrice") != null) {
			returnVal.setUnitPrice((BigDecimal) fields.get("unitPrice"));
}

		if(fields.get("reserv2ndPPPerc") != null) {
			returnVal.setReserv2ndPPPerc((BigDecimal) fields.get("reserv2ndPPPerc"));
}

		if(fields.get("reservNthPPPerc") != null) {
			returnVal.setReservNthPPPerc((BigDecimal) fields.get("reservNthPPPerc"));
}

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("totalWithAdjustments") != null) {
			returnVal.setTotalWithAdjustments((BigDecimal) fields.get("totalWithAdjustments"));
}

		if(fields.get("wasReserved") != null) {
			returnVal.setWasReserved((boolean) fields.get("wasReserved"));
}


		return returnVal;
 } 
	public static CartAbandonedLine mapstrstr(Map<String, String> fields) throws Exception {

		CartAbandonedLine returnVal = new CartAbandonedLine();

		if(fields.get("visitId") != null) {
			returnVal.setVisitId((String) fields.get("visitId"));
}

		if(fields.get("cartAbandonedLineSeqId") != null) {
			returnVal.setCartAbandonedLineSeqId((String) fields.get("cartAbandonedLineSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("reservStart") != null) {
String buf = fields.get("reservStart");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReservStart(ibuf);
}

		if(fields.get("reservLength") != null) {
String buf;
buf = fields.get("reservLength");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservLength(bd);
}

		if(fields.get("reservPersons") != null) {
String buf;
buf = fields.get("reservPersons");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}

		if(fields.get("unitPrice") != null) {
String buf;
buf = fields.get("unitPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitPrice(bd);
}

		if(fields.get("reserv2ndPPPerc") != null) {
String buf;
buf = fields.get("reserv2ndPPPerc");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReserv2ndPPPerc(bd);
}

		if(fields.get("reservNthPPPerc") != null) {
String buf;
buf = fields.get("reservNthPPPerc");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservNthPPPerc(bd);
}

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("totalWithAdjustments") != null) {
String buf;
buf = fields.get("totalWithAdjustments");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalWithAdjustments(bd);
}

		if(fields.get("wasReserved") != null) {
String buf;
buf = fields.get("wasReserved");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setWasReserved(ibuf);
}


		return returnVal;
 } 
	public static CartAbandonedLine map(GenericValue val) {

CartAbandonedLine returnVal = new CartAbandonedLine();
		returnVal.setVisitId(val.getString("visitId"));
		returnVal.setCartAbandonedLineSeqId(val.getString("cartAbandonedLineSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setReservStart(val.getTimestamp("reservStart"));
		returnVal.setReservLength(val.getBigDecimal("reservLength"));
		returnVal.setReservPersons(val.getBigDecimal("reservPersons"));
		returnVal.setUnitPrice(val.getBigDecimal("unitPrice"));
		returnVal.setReserv2ndPPPerc(val.getBigDecimal("reserv2ndPPPerc"));
		returnVal.setReservNthPPPerc(val.getBigDecimal("reservNthPPPerc"));
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setTotalWithAdjustments(val.getBigDecimal("totalWithAdjustments"));
		returnVal.setWasReserved(val.getBoolean("wasReserved"));


return returnVal;

}

public static CartAbandonedLine map(HttpServletRequest request) throws Exception {

		CartAbandonedLine returnVal = new CartAbandonedLine();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("visitId")) {
returnVal.setVisitId(request.getParameter("visitId"));
}

		if(paramMap.containsKey("cartAbandonedLineSeqId"))  {
returnVal.setCartAbandonedLineSeqId(request.getParameter("cartAbandonedLineSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("prodCatalogId"))  {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("reservStart"))  {
String buf = request.getParameter("reservStart");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReservStart(ibuf);
}
		if(paramMap.containsKey("reservLength"))  {
String buf = request.getParameter("reservLength");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservLength(bd);
}
		if(paramMap.containsKey("reservPersons"))  {
String buf = request.getParameter("reservPersons");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}
		if(paramMap.containsKey("unitPrice"))  {
String buf = request.getParameter("unitPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitPrice(bd);
}
		if(paramMap.containsKey("reserv2ndPPPerc"))  {
String buf = request.getParameter("reserv2ndPPPerc");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReserv2ndPPPerc(bd);
}
		if(paramMap.containsKey("reservNthPPPerc"))  {
String buf = request.getParameter("reservNthPPPerc");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservNthPPPerc(bd);
}
		if(paramMap.containsKey("configId"))  {
returnVal.setConfigId(request.getParameter("configId"));
}
		if(paramMap.containsKey("totalWithAdjustments"))  {
String buf = request.getParameter("totalWithAdjustments");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalWithAdjustments(bd);
}
		if(paramMap.containsKey("wasReserved"))  {
String buf = request.getParameter("wasReserved");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setWasReserved(ibuf);
}
return returnVal;

}
}
