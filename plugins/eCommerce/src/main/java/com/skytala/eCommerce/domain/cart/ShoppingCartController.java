package com.skytala.eCommerce.domain.cart;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ofbiz.base.util.UtilMisc;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.ProductController;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.product.relations.product.control.price.ProductPriceController;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.badRequest;
import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.successful;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.HAS_USER_AUTHORITY;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

	@Resource
	ProductController productController;

	@Resource
	ProductPriceController priceController;

	@RequestMapping(method = RequestMethod.GET, value = "/show")
	@PreAuthorize(HAS_USER_AUTHORITY)
	public ResponseEntity show(HttpSession session) {

		if (session.getAttribute("cart") != null) {
			ShoppingCart sc = new ShoppingCart();
			sc = (ShoppingCart) session.getAttribute("cart");
			return successful(sc.getPositions());
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showId")
	@PreAuthorize(HAS_USER_AUTHORITY)
	public ResponseEntity showId(HttpSession session) {

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
	@PreAuthorize(HAS_USER_AUTHORITY)
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
	@PreAuthorize(HAS_USER_AUTHORITY)
	public ResponseEntity addToCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) throws Exception {

		BigDecimal anz = new BigDecimal(1);

		if(allRequestParams.get("count") == null)
			throw new IllegalArgumentException("no count given");
		else{
			anz = new BigDecimal(Integer.parseInt(allRequestParams.get("count")));

			if(anz.compareTo(BigDecimal.ZERO)<0)
				throw new IllegalArgumentException("while adding count is not supposed to be negative; please use the remove function");
		}
		if (session.getAttribute("cart") == null) {
			ShoppingCart sc = new ShoppingCart();
			session.setAttribute("cart", sc);
		}
		ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");
		Product pro = new Product();

		
		try{
			if(allRequestParams.get("productId") != null){				
			pro = productController.findById(allRequestParams.get("productId")).getBody();
			}
			else{
				return badRequest();
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getMessage());

		}



		if (!pro.getProductId().equals(allRequestParams.get("productId"))) {
			return badRequest();
		}



		for (int i = 0; i < sc.getPositions().size(); i++) {
			if (pro.getProductId().equals(sc.getPositions().get(i).getProduct().getProductId())) {
				BigDecimal ibuf = sc.getPositions().get(i).getNumberProducts();
				sc.getPositions().get(i).setNumberProducts(ibuf.add(anz));
				sc.setGrandTotal(calculateGrandTotal(sc));
				session.setAttribute("cart", sc);
				return successful();

			}

		}

		Position pos = new Position(pro, anz);
		sc.addPosition(pos);
		sc.setGrandTotal(calculateGrandTotal(sc));

		
		session.setAttribute("cart", sc);
		return successful();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/remove")
	@PreAuthorize(HAS_USER_AUTHORITY)
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
						sc.setGrandTotal(this.calculateGrandTotal(sc));
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

	public BigDecimal calculateGrandTotal(ShoppingCart sc) throws Exception {


		List<Position> positions = sc.getPositions();
		List<ProductPrice> prices;
		BigDecimal returnVal = new BigDecimal(0);

		for (int i = 0; i < positions.size(); i++) {
			String productId = positions.get(i).getProduct().getProductId();
			Map<String, String> filter = UtilMisc.toMap("productId",productId,"productPriceTypeId","DEFAULT_PRICE");
			List<ProductPrice> responseVal = priceController.findProductPricesBy(filter).getBody();

			ProductPrice newestPrice = priceController.getNewestPrice(responseVal);

			if(newestPrice!=null) {
				returnVal = returnVal.add(newestPrice.getPrice().multiply(positions.get(i).getNumberProducts()));
			} else {
				throw new Exception("No default price, or multiple default prices");
			}


		}

		return returnVal;
	}
}
