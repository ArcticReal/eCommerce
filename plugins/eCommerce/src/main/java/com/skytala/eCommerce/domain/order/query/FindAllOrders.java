package com.skytala.eCommerce.domain.order.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.order.event.OrdersFound;
import com.skytala.eCommerce.domain.order.header.OrderHeader;
import com.skytala.eCommerce.domain.order.header.OrderHeaderMapper;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Query;

public class FindAllOrders extends Query {

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		List<OrderHeader> returnVal = new LinkedList<OrderHeader>();
		try {
			List<GenericValue> orders = delegator.findAll("OrderHeader", false);

			for (int i = 0; i < orders.size(); i++) {
				returnVal.add(OrderHeaderMapper.map(orders.get(i)));
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		Broker.instance().publish(new OrdersFound(returnVal));
		return null;

	}

}
