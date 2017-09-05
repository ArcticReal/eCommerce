package com.skytala.eCommerce.control;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.entity.Position;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ShoppingCart;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

	@RequestMapping(method = RequestMethod.GET, value = { "/show", "/" })
	public LinkedList<Position> show(HttpSession session) {

		// Debug Message for Windows Forms should be removed later
		System.out.println(session.getCreationTime());
		System.out.println(session.getId());

		if (session.getAttribute("cart") != null) {
			ShoppingCart sc = new ShoppingCart();
			sc = (ShoppingCart) session.getAttribute("cart");
			return sc.getPositions();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showId")
	public LinkedList<String> showId(HttpSession session) {
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
		return mylist;

	}

	@RequestMapping(method = RequestMethod.GET, value = { "/total", "/grandTotal", "/calc" })
	public BigDecimal showGrandTotal(HttpSession session) {

		BigDecimal grandTotal = new BigDecimal(0);
		if (session.getAttribute("cart") != null) {
			ShoppingCart sc = new ShoppingCart();
			sc = (ShoppingCart) session.getAttribute("cart");
			grandTotal = sc.getGrandTotal();
		}

		return grandTotal;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/add", "/create",
			"/new" }, consumes = "application/x-www-form-urlencoded")
	public boolean addToCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) {

		Map<String, String> find = new HashMap<String, String>();
		ProductController pc = new ProductController();

		// Debug Message for Windows Forms should be removed later
		System.out.println(session.getCreationTime());
		System.out.println(session.getId());

		if (session.getAttribute("cart") == null) {
			ShoppingCart sc = new ShoppingCart();
			session.setAttribute("cart", sc);
		}
		ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");

		if (allRequestParams.get("productId") != null) {
			find.put("productId", allRequestParams.get("productId"));
			System.out.println(allRequestParams.get("productId"));
		} else {
			return false;
		}

		List<Product> products = pc.findBy(find);
		Product pro = new Product();
		for (int i = 0; i < products.size(); i++) {
			pro = pc.findBy(find).get(i);

			if (pro.getProductId().equals(allRequestParams.get("productId"))) {
				break;
			} else {
				pro = null;
			}
		}

		if (pro == null) {
			return false;
		}

		BigDecimal anz = new BigDecimal(1);

		if (allRequestParams.get("count") != null) {
			anz = new BigDecimal(Integer.parseInt(allRequestParams.get("count")));
			System.out.println(allRequestParams.get("count"));
		}

		for (int i = 0; i < sc.getPositions().size(); i++) {
			if (pro.getProductId().equals(sc.getPositions().get(i).getProduct().getProductId())) {
				BigDecimal ibuf = sc.getPositions().get(i).getNumberProducts();
				sc.getPositions().get(i).setNumberProducts(ibuf.add(anz));
				session.setAttribute("cart", sc);
				return true;

			}

		}

		Position pos = new Position(pro, anz);
		sc.addPosition(pos);
		try {
			sc.setGrandTotal(this.calculateGrandTotal(sc));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		session.setAttribute("cart", sc);
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/remove",
			"/delete" }, consumes = "application/x-www-form-urlencoded")
	public boolean removeFromCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) {

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
					return true;
				}
			}

			// sc.removebyPosition(Integer.parseInt(allRequestParams.get("position")));
		}

		return false;
	}

	public BigDecimal calculateGrandTotal(ShoppingCart sc) throws Exception {

		PricingController pc = new PricingController();

		LinkedList<Position> positions = sc.getPositions();
		List<ProductPrice> prices;
		BigDecimal returnVal = new BigDecimal(0);

		for (int i = 0; i < positions.size(); i++) {
			String productId = positions.get(i).getProduct().getProductId();
			prices = pc.findProductPricesBy(productId, "DEFAULT_PRICE");

			if (prices.size() == 1) {
				returnVal = returnVal.add(prices.get(0).getPrice().multiply(positions.get(i).getNumberProducts()));
			} else {
				throw new Exception("No default price, or multiple default prices");
			}
		}

		return returnVal;
	}
}
