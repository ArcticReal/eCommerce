package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.entity.Position;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ShoppingCart;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public LinkedList<Position> show(HttpSession session) {

		ShoppingCart sc = new ShoppingCart();
		if (session.getAttribute("cart") != null) {
			sc = (ShoppingCart) session.getAttribute("cart");
			return sc.getPositions();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/x-www-form-urlencoded")
	public boolean addToCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) {

		Map<String, String> find = new HashMap<String, String>();
		ProductController pc = new ProductController();

		if (session.getAttribute("cart") == null) {
			ShoppingCart sc = new ShoppingCart();
			session.setAttribute("cart", sc);
		}
		ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");
		Product pro = new Product();

		if (allRequestParams.get("productId") != null) {
			find.put("productId", allRequestParams.get("productId"));
			System.out.println(allRequestParams.get("productId"));
		}
		pro = pc.findBy(find).get(0);
		int anz = 0;
		if (allRequestParams.get("count") != null) {
			anz = Integer.parseInt(allRequestParams.get("count"));
			System.out.println(allRequestParams.get("count"));
		}

		Position pos = new Position(pro, anz);

		sc.addPosition(pos);
		session.setAttribute("cart", sc);
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/remove", consumes = "application/x-www-form-urlencoded")
	public boolean removeFromCart(HttpSession session, @RequestParam Map<String, String> allRequestParams) {

		if (allRequestParams.get("position") != null) {
			ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");
			sc.removebyPosition(Integer.parseInt(allRequestParams.get("position")));
			session.setAttribute("cart", sc);
			return true;
		}

		return false;
	}
}
