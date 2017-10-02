package com.skytala.eCommerce.domain.order.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;

import com.skytala.eCommerce.domain.order.event.SpecificOrderFound;
import com.skytala.eCommerce.domain.order.header.OrderHeader;
import com.skytala.eCommerce.domain.order.header.OrderHeaderMapper;
import com.skytala.eCommerce.domain.order.item.OrderItem;
import com.skytala.eCommerce.domain.order.item.OrderItemMapper;
import com.skytala.eCommerce.domain.order.model.Order;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindOrderById extends Query {

	private String orderId;

	public FindOrderById(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		Order order = new Order();

		try {
			GenericValue value = delegator.findOne("OrderHeader", false, UtilMisc.toMap("orderId", orderId));
			OrderHeader orderHeader = OrderHeaderMapper.map(value);

			EntityCondition cond = EntityCondition.makeCondition(UtilMisc.toMap("orderId", orderId));

			List<GenericValue> values = delegator.findList("OrderItem", cond, null, null, null, false);

			List<OrderItem> items = new LinkedList<>();

			for (int i = 0; i < values.size(); i++) {
				items.add(OrderItemMapper.map(values.get(i)));

			}

			order.setHeader(orderHeader);
			order.setItems(items);

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		
		Broker.instance().publish(new SpecificOrderFound(order));
		return null;

	}

}
