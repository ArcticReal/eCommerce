
package com.skytala.eCommerce.domain.order.relations.orderItemBilling.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.event.OrderItemBillingFound;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.mapper.OrderItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;


public class FindAllOrderItemBillings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemBilling> returnVal = new ArrayList<OrderItemBilling>();
try{
List<GenericValue> results = delegator.findAll("OrderItemBilling", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemBillingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemBillingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
