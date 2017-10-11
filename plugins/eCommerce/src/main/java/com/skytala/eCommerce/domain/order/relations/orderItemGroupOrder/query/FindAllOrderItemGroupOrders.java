
package com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.event.OrderItemGroupOrderFound;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.mapper.OrderItemGroupOrderMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.model.OrderItemGroupOrder;


public class FindAllOrderItemGroupOrders extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemGroupOrder> returnVal = new ArrayList<OrderItemGroupOrder>();
try{
List<GenericValue> results = delegator.findAll("OrderItemGroupOrder", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemGroupOrderMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemGroupOrderFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
