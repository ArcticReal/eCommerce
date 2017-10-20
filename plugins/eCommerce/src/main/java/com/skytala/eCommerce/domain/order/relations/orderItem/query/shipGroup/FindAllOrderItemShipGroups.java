
package com.skytala.eCommerce.domain.order.relations.orderItem.query.shipGroup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup.OrderItemShipGroupFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGroup.OrderItemShipGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;


public class FindAllOrderItemShipGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemShipGroup> returnVal = new ArrayList<OrderItemShipGroup>();
try{
List<GenericValue> results = delegator.findAll("OrderItemShipGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemShipGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemShipGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
