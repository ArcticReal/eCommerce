
package com.skytala.eCommerce.domain.order.relations.orderHeader.query.workEffort;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.workEffort.OrderHeaderWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.workEffort.OrderHeaderWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.workEffort.OrderHeaderWorkEffort;


public class FindAllOrderHeaderWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderHeaderWorkEffort> returnVal = new ArrayList<OrderHeaderWorkEffort>();
try{
List<GenericValue> results = delegator.findAll("OrderHeaderWorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderHeaderWorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderHeaderWorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
