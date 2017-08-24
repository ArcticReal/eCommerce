package com.companyname.skytalaPlugin.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/control")
public class ProductListController {
	
	@RequestMapping("findProducts")
	public ArrayList<Product> findProducts(@RequestParam(value = "name", defaultValue = "") String name, 
											@RequestParam(value = "id", defaultValue = "") String id) {
	
		
		System.out.println("FFFFFFFFF");

		ArrayList<Product> productList = new ArrayList<Product>();
		
		Delegator delegator = DelegatorFactory.getDelegator("default");

		try {
		List<GenericValue> buf = delegator.findAll("Product", false);

			for (int i = 0; i < buf.size(); i++) {
				if(buf.get(i).getString("productName") != null && 
						(buf.get(i).getString("productName").contains(name) && buf.get(i).getString("productId").contains(id))) {
					productList.add(new Product(buf.get(i).getString("productName"), buf.get(i).getString("productId")));
				}
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
	
		return productList;
	}
	
	
	
}
