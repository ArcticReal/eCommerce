
package com.skytala.eCommerce.domain.order.relations.orderAdjustment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.OrderAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.OrderAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.OrderAdjustment;


public class FindAllOrderAdjustments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAdjustment> returnVal = new ArrayList<OrderAdjustment>();
try{
List<GenericValue> results = delegator.findAll("OrderAdjustment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderAdjustmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderAdjustmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
