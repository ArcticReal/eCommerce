
package com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.event.OrderAdjustmentBillingFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.mapper.OrderAdjustmentBillingMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentBilling.model.OrderAdjustmentBilling;


public class FindAllOrderAdjustmentBillings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAdjustmentBilling> returnVal = new ArrayList<OrderAdjustmentBilling>();
try{
List<GenericValue> results = delegator.findAll("OrderAdjustmentBilling", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderAdjustmentBillingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderAdjustmentBillingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
