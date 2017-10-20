
package com.skytala.eCommerce.domain.order.relations.orderItem.query.group;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.group.OrderItemGroupFound;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.group.OrderItemGroupMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.group.OrderItemGroup;


public class FindAllOrderItemGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemGroup> returnVal = new ArrayList<OrderItemGroup>();
try{
List<GenericValue> results = delegator.findAll("OrderItemGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
