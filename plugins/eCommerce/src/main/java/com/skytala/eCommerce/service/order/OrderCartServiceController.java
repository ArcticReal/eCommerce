package com.skytala.eCommerce.service.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/orderCart")
public class OrderCartServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/setCartOtherOptions")
	public ResponseEntity<Object> setCartOtherOptions(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="orderAdditionalEmails", required=false) String orderAdditionalEmails, @RequestParam(value="correspondingPoId", required=false) String correspondingPoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("orderAdditionalEmails",orderAdditionalEmails);
		paramMap.put("correspondingPoId",correspondingPoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCartOtherOptions", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCartAbandonedLine")
	public ResponseEntity<Object> deleteCartAbandonedLine(HttpSession session, @RequestParam(value="visitId") String visitId, @RequestParam(value="cartAbandonedLineSeqId") String cartAbandonedLineSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visitId",visitId);
		paramMap.put("cartAbandonedLineSeqId",cartAbandonedLineSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCartAbandonedLine", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceProductPromoCond")
	public ResponseEntity<Object> interfaceProductPromoCond(HttpSession session, @RequestParam(value="productPromoCond") org.apache.ofbiz.entity.GenericValue productPromoCond, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="nowTimestamp") Timestamp nowTimestamp) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoCond",productPromoCond);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("nowTimestamp",nowTimestamp);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceProductPromoCond", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loadCartFromQuote")
	public ResponseEntity<Object> loadCartFromQuote(HttpSession session, @RequestParam(value="quoteId") String quoteId, @RequestParam(value="applyQuoteAdjustments", required=false) String applyQuoteAdjustments) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quoteId",quoteId);
		paramMap.put("applyQuoteAdjustments",applyQuoteAdjustments);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("loadCartFromQuote", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateCartAbandonedLine")
	public ResponseEntity<Object> updateCartAbandonedLine(HttpSession session, @RequestParam(value="visitId") String visitId, @RequestParam(value="cartAbandonedLineSeqId") String cartAbandonedLineSeqId, @RequestParam(value="unitPrice", required=false) BigDecimal unitPrice, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="totalWithAdjustments", required=false) BigDecimal totalWithAdjustments, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="wasReserved", required=false) String wasReserved, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visitId",visitId);
		paramMap.put("cartAbandonedLineSeqId",cartAbandonedLineSeqId);
		paramMap.put("unitPrice",unitPrice);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("totalWithAdjustments",totalWithAdjustments);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("wasReserved",wasReserved);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCartAbandonedLine", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCartShippingAddress")
	public ResponseEntity<Object> setCartShippingAddress(HttpSession session, @RequestParam(value="shippingContactMechId") String shippingContactMechId, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="groupIndex") Integer groupIndex) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shippingContactMechId",shippingContactMechId);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("groupIndex",groupIndex);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCartShippingAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loadCartFromShoppingList")
	public ResponseEntity<Object> loadCartFromShoppingList(HttpSession session, @RequestParam(value="shoppingListId") String shoppingListId, @RequestParam(value="orderPartyId", required=false) String orderPartyId, @RequestParam(value="applyStorePromotions", required=false) String applyStorePromotions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingListId",shoppingListId);
		paramMap.put("orderPartyId",orderPartyId);
		paramMap.put("applyStorePromotions",applyStorePromotions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("loadCartFromShoppingList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prepareVendorShipGroups")
	public ResponseEntity<Object> prepareVendorShipGroups(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prepareVendorShipGroups", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getShoppingCartItemIndex")
	public ResponseEntity<Object> getShoppingCartItemIndex(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getShoppingCartItemIndex", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createCartAbandonedLine")
	public ResponseEntity<Object> createCartAbandonedLine(HttpSession session, @RequestParam(value="visitId") String visitId, @RequestParam(value="cartAbandonedLineSeqId") String cartAbandonedLineSeqId, @RequestParam(value="unitPrice", required=false) BigDecimal unitPrice, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="reservLength", required=false) BigDecimal reservLength, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="reservPersons", required=false) BigDecimal reservPersons, @RequestParam(value="totalWithAdjustments", required=false) BigDecimal totalWithAdjustments, @RequestParam(value="reservStart", required=false) Timestamp reservStart, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="wasReserved", required=false) String wasReserved, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("visitId",visitId);
		paramMap.put("cartAbandonedLineSeqId",cartAbandonedLineSeqId);
		paramMap.put("unitPrice",unitPrice);
		paramMap.put("quantity",quantity);
		paramMap.put("reservLength",reservLength);
		paramMap.put("productId",productId);
		paramMap.put("reservPersons",reservPersons);
		paramMap.put("totalWithAdjustments",totalWithAdjustments);
		paramMap.put("reservStart",reservStart);
		paramMap.put("configId",configId);
		paramMap.put("wasReserved",wasReserved);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCartAbandonedLine", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceProductPromoAction")
	public ResponseEntity<Object> interfaceProductPromoAction(HttpSession session, @RequestParam(value="productPromoAction") org.apache.ofbiz.entity.GenericValue productPromoAction, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="nowTimestamp") Timestamp nowTimestamp, @RequestParam(value="actionResultInfo") org.apache.ofbiz.order.shoppingcart.product.ProductPromoWorker.ActionResultInfo actionResultInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoAction",productPromoAction);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("nowTimestamp",nowTimestamp);
		paramMap.put("actionResultInfo",actionResultInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceProductPromoAction", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCartShippingOptions")
	public ResponseEntity<Object> setCartShippingOptions(HttpSession session, @RequestParam(value="shippingContactMechId") String shippingContactMechId, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="maySplit") Boolean maySplit, @RequestParam(value="isGift") Boolean isGift, @RequestParam(value="groupIndex") Integer groupIndex, @RequestParam(value="shipmentMethodString") String shipmentMethodString, @RequestParam(value="giftMessage", required=false) String giftMessage, @RequestParam(value="shippingInstructions", required=false) String shippingInstructions) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shippingContactMechId",shippingContactMechId);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("maySplit",maySplit);
		paramMap.put("isGift",isGift);
		paramMap.put("groupIndex",groupIndex);
		paramMap.put("shipmentMethodString",shipmentMethodString);
		paramMap.put("giftMessage",giftMessage);
		paramMap.put("shippingInstructions",shippingInstructions);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCartShippingOptions", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loadCartFromOrder")
	public ResponseEntity<Object> loadCartFromOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="createAsNewOrder") String createAsNewOrder, @RequestParam(value="includePromoItems", required=false) Boolean includePromoItems, @RequestParam(value="skipProductChecks", required=false) Boolean skipProductChecks, @RequestParam(value="skipInventoryChecks", required=false) Boolean skipInventoryChecks) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("createAsNewOrder",createAsNewOrder);
		paramMap.put("includePromoItems",includePromoItems);
		paramMap.put("skipProductChecks",skipProductChecks);
		paramMap.put("skipInventoryChecks",skipInventoryChecks);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("loadCartFromOrder", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getShoppingCartData")
	public ResponseEntity<Object> getShoppingCartData(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getShoppingCartData", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resetShipGroupItems")
	public ResponseEntity<Object> resetShipGroupItems(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resetShipGroupItems", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/assignItemShipGroup")
	public ResponseEntity<Object> assignItemShipGroup(HttpSession session, @RequestParam(value="quantity") BigDecimal quantity, @RequestParam(value="fromGroupIndex") Integer fromGroupIndex, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="itemIndex") Integer itemIndex, @RequestParam(value="toGroupIndex") Integer toGroupIndex, @RequestParam(value="clearEmptyGroups", required=false) Boolean clearEmptyGroups) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quantity",quantity);
		paramMap.put("fromGroupIndex",fromGroupIndex);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("itemIndex",itemIndex);
		paramMap.put("toGroupIndex",toGroupIndex);
		paramMap.put("clearEmptyGroups",clearEmptyGroups);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("assignItemShipGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setCartPaymentOptions")
	public ResponseEntity<Object> setCartPaymentOptions(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="paymentInfoId") String paymentInfoId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="refNum", required=false) String refNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("paymentInfoId",paymentInfoId);
		paramMap.put("amount",amount);
		paramMap.put("refNum",refNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setCartPaymentOptions", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

}
