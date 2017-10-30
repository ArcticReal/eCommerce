package com.skytala.eCommerce.domain.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;
import org.apache.ofbiz.base.util.UtilMisc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.ProductController;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.control.price.ProductPriceController;
import com.skytala.eCommerce.domain.cart.ShoppingCart;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.badRequest;
import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.successful;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

	@Resource
	ProductController pc;

	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public ResponseEntity show(HttpSession session) {

		// Debug Message for Windows Forms should be removed later
		System.out.println(session.getCreationTime());
		System.out.println(session.getId());

		if (session.getAttribute("cart") != null) {
			ShoppingCart sc = new ShoppingCart();
			sc = (ShoppingCart) session.getAttribute("cart");
			return successful(sc.getPositions());
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showId")
	public ResponseEntity showId(HttpSession session) {
		// Debug Message for Windows Forms should be removed later
		System.out.println(session.getCreationTime());
		System.out.println(session.getId());

		LinkedList<String> mylist = new LinkedList<String>();
		if (session.getAttribute("cart") != null) {
			ShoppingCart sc = new ShoppingCart();
			sc = (ShoppingCart) session.getAttribute("cart");
			for (int i = 0; i < sc.getPositions().size(); i++) {
				mylist.add(sc.getPositions().get(i).getProduct().getProductId());
			}
		}
		return successful(mylist);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/total")
	public ResponseEntity showGrandTotal(HttpSession session) {

		BigDecimal grandTotal = new BigDecimal(0);
		if (session.getAttribute("cart") != null) {
			ShoppingCart sc = new ShoppingCart();
			sc = (ShoppingCart) session.getAttribute("cart");
			grandTotal = sc.getGrandTotal();
		}

		return successful(grandTotal);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity addToCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) {


		// Debug Message for Windows Forms should be removed later
		System.out.println(session.getCreationTime());
		System.out.println(session.getId());

		if (session.getAttribute("cart") == null) {
			ShoppingCart sc = new ShoppingCart();
			session.setAttribute("cart", sc);
		}
		ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");
		Product pro = new Product();

		
		try{
			if(allRequestParams.get("productId") != null){				
			pro = pc.findById(allRequestParams.get("productId")).getBody();
			}
			else{
				return badRequest();
			}
		}catch (Exception e){
			// do smthg
		}

		BigDecimal anz = new BigDecimal(1);

		if (!pro.getProductId().equals(allRequestParams.get("productId"))) {
			return badRequest();
		}

		if (allRequestParams.get("count") != null) {
			anz = new BigDecimal(Integer.parseInt(allRequestParams.get("count")));
			System.out.println(allRequestParams.get("count"));
		}

		for (int i = 0; i < sc.getPositions().size(); i++) {
			if (pro.getProductId().equals(sc.getPositions().get(i).getProduct().getProductId())) {
				BigDecimal ibuf = sc.getPositions().get(i).getNumberProducts();
				sc.getPositions().get(i).setNumberProducts(ibuf.add(anz));
				session.setAttribute("cart", sc);
				return successful();

			}

		}

		Position pos = new Position(pro, anz);
		sc.addPosition(pos);
		try {
			sc.setGrandTotal(this.calculateGrandTotal(sc).getBody());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		session.setAttribute("cart", sc);
		return successful();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/remove")
	public ResponseEntity removeFromCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) {

		if (allRequestParams.get("productId") != null) {
			int count = Integer.parseInt(allRequestParams.get("count"));
			ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");

			for (int i = 0; i < sc.getPositions().size(); i++) {
				if (sc.getPositions().get(i).getProduct().getProductId().equals(allRequestParams.get("productId"))) {
					sc.getPositions().get(i).setNumberProducts(
							sc.getPositions().get(i).getNumberProducts().subtract(new BigDecimal(count)));
					if (sc.getPositions().get(i).getNumberProducts().signum() <= 0) {
						sc.removebyPosition(i);
					}
					try {
						sc.setGrandTotal(this.calculateGrandTotal(sc).getBody());
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					session.setAttribute("cart", sc);
					return successful();
				}
			}

		}

		return badRequest();
	}

	public ResponseEntity<BigDecimal> calculateGrandTotal(ShoppingCart sc) throws Exception {

		ProductPriceController pc = new ProductPriceController();

		LinkedList<Position> positions = sc.getPositions();
		List<ProductPrice> prices;
		BigDecimal returnVal = new BigDecimal(0);

		for (int i = 0; i < positions.size(); i++) {
			String productId = positions.get(i).getProduct().getProductId();
			Map<String, String> filter = UtilMisc.toMap("productId",productId,"productPriceTypeId","DEFAULT_PRICE");
			Object responseVal = pc.findProductPricesBy(filter).getBody();

			if(responseVal.getClass().equals(ProductPrice.class)){
				returnVal = returnVal.add(((ProductPrice)responseVal).getPrice().multiply(positions.get(i).getNumberProducts()));
			} else {
				throw new Exception("No default price, or multiple default prices");
			}


		}

		return successful(returnVal);
	}
}
