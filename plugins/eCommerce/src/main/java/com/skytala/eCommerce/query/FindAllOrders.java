package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.OrderHeader;
import com.skytala.eCommerce.entity.OrderHeaderMapper;
import com.skytala.eCommerce.event.OrdersFound;

public class FindAllOrders implements Query {

	@Override
	public void execute() {

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

	}

}
