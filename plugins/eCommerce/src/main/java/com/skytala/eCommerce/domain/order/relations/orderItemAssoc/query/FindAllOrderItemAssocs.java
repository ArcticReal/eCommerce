
package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.event.OrderItemAssocFound;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.mapper.OrderItemAssocMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;


public class FindAllOrderItemAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderItemAssoc> returnVal = new ArrayList<OrderItemAssoc>();
try{
List<GenericValue> results = delegator.findAll("OrderItemAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderItemAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderItemAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
