package com.skytala.eCommerce.domain.order.command;

import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.cart.ShoppingCart;
import com.skytala.eCommerce.domain.order.event.OrderCreated;
import com.skytala.eCommerce.domain.order.header.OrderHeader;
import com.skytala.eCommerce.domain.order.header.OrderHeaderMapper;
import com.skytala.eCommerce.domain.order.item.OrderItem;
import com.skytala.eCommerce.domain.order.item.OrderItemMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class NewOrder extends Command{


	ShoppingCart cart;
	OrderHeader header;
	
	public NewOrder(ShoppingCart cart) {
		this.cart = cart;
		header = new OrderHeader();
	}
	
	@Override
	public Event execute() {
		Delegator delegator = DelegatorFactory.getDelegator("default");

		String headerId = null;
		boolean success;
		try {
			headerId = delegator.getNextSeqId("OrderHeader");
			header.setOrderId(headerId);
			header.setGrandTotal(cart.getGrandTotal());
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
				item.setStatusId("ITEM_CREATED");
				
				
				
				try {
					
					
					GenericValue newValue = delegator.makeValue("OrderItem", OrderItemMapper.map(item));
					delegator.create(newValue);
				}catch(GenericEntityException e) {
					e.printStackTrace();
					success = false;
					break;
				}
			}
		}
		
		
		Broker.instance().publish(new OrderCreated(success));
		return null;
		
	}

}
