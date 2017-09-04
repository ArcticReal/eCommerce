package com.skytala.eCommerce.command;

import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.OrderHeader;
import com.skytala.eCommerce.entity.OrderHeaderMapper;
import com.skytala.eCommerce.entity.OrderItem;
import com.skytala.eCommerce.entity.Position;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.ShoppingCart;
import com.skytala.eCommerce.event.OrderCreated;

public class NewOrder implements Command{


	ShoppingCart cart;
	OrderHeader header;
	
	public NewOrder(ShoppingCart cart) {
		this.cart = cart;
	}
	
	@Override
	public void execute() {
		Delegator delegator = DelegatorFactory.getDelegator("default");

		String headerId = null;
		boolean success;
		try {
			headerId = delegator.getNextSeqId("OrderHeader");
			header.setOrderId(headerId);
			GenericValue newValue = delegator.makeValue("OrderHeader", OrderHeaderMapper.map(header));
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		if(success) {
			
			LinkedList<Position> positions = cart.getPositions();
			
			for(int i = 0; i < positions.size(); i++) {
				Product product = positions.get(i).getProduct();	
				OrderItem item = new OrderItem();
				item.setOrderId(headerId);
				item.setOrderItemSeqId(Integer.toString(i));
				item.setOrderItemTypeId("PRODUCT_ORDER_ITEM");
				item.setProductId(product.getProductId());
				item.setQuantity(positions.get(i).getNumberProducts());
				
				//TODO: pricing
				
				item.setStatusId("ITEM_CREATED");
				
				try {
					GenericValue newValue = delegator.makeValue("OrderItem", item);
					delegator.create(newValue);
				}catch(GenericEntityException e) {
					e.printStackTrace();
					success = false;
					break;
				}
			}
		}
		
		
		Broker.instance().publish(new OrderCreated(success));
		
	}

}
