package com.companyname.skytalaPlugin.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class Products {

	public static Map<String, Object> search(DispatchContext dctx, Map<String, Object> context) {
		Map<String, Object> result;
		result = ServiceUtil.returnSuccess();
		Delegator delegator = dctx.getDelegator();
		String findId = null;
		String findName = null;

		if (context.get("ID") == null && context.get("Name") == null) {

			try {
				List<GenericValue> buf = delegator.findAll("Product", false);

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

		} else {
			try {
				Map<String, String> compare = new HashMap<String, String>();
				
				if(context.get("ID")!=null){	findId = "" + context.get("ID");	}
				
				if(context.get("Name") !=null){ findName="nvidia";			}

				List<String> order = new ArrayList<String>();
				order.add("productId");
				order.add("productName");
				List<GenericValue> buf;

				compare.put("productId", findId);
				if (findId!=null) {
					compare.put("productId",findId);
					System.out.println("Compared findId:  "+findId);
				}
				
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

		/*
		 * JFrame externproduct = new JFrame("Beispiel Frame");
		 * externproduct.setSize(400, 400); externproduct.setVisible(true);
		 * 
		 * JLabel text = new JLabel(); externproduct.add(text);
		 * text.setText(""+context.get("ID"));
		 * 
		 */

		return result;
	}
}
