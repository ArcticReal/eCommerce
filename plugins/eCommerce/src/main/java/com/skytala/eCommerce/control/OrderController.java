package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.entity.ShoppingCart;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@RequestMapping("/create")
	public void createOrder(ShoppingCart cart) {
	
		Delegator delegator = DelegatorFactory.getDelegator("default");

		try {
			Map<String, String> map = new HashMap<>();
			map.put("uomTypeId", "CURRENCY_MEASURE");
			map.put("description", "Euro");
			System.out.println(delegator.findByAnd("Uom", map, null, false)+"\n\n");
		} catch (GenericEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
