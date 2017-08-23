package com.companyname.skytalaPlugin.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.webapp.control.ControlServlet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/control")
public class ProductListController {

	ControlServlet controlServlet = new ControlServlet();
	
	@RequestMapping("findProducts")
	public ArrayList<Product> findProducts() {
		
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String id = null;
		String name = null;
		
		Delegator delegator = DelegatorFactory.getDelegator("default");
		String findId = null;
		String findName = null;

		if (id == null && name == null) {

			
			try {
				List<GenericValue> buf = delegator.findAll("Product", false);

				for (int i = 0; i < buf.size(); i++) {

					// result.put("Id",
					// buf.get(i).getString("skytalaPluginId"));
					System.out.println("Die Product Id:  " + buf.get(i).getString("productId")
							+ "  und der Name des Produktes ist:  " + buf.get(i).getString("productName"));
					
					productList.add(new Product(buf.get(i).getString("productName"), buf.get(i).getString("productId")));

				}

			} catch (GenericEntityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				Map<String, String> compare = new HashMap<String, String>();
				
				if(name !=null){ findName="nvidia";			}

				List<String> order = new ArrayList<String>();
				order.add("productId");
				order.add("productName");
				List<GenericValue> buf;

				compare.put("productId", findId);
				if (findName!=null) {
					compare.put("productName", findName);
					System.out.println("Compared Name:    "+findName);
				}
				

				buf = delegator.findByAnd("Product", compare, order, false);
				for (int i = 0; i < buf.size(); i++) {

					// result.put("Id",
					// buf.get(i).getString("skytalaPluginId"));
					
					System.out.println("Die Product Id:  " + buf.get(i).getString("productId")
							+ "  und der Name des Produktes ist:  " + buf.get(i).getString("productName"));

				}
			} catch (GenericEntityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



		
		return productList;
	}
	
}
