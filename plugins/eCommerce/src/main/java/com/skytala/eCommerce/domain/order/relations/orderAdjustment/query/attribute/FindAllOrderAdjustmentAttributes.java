
package com.skytala.eCommerce.domain.order.relations.orderAdjustment.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.event.attribute.OrderAdjustmentAttributeFound;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.attribute.OrderAdjustmentAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute.OrderAdjustmentAttribute;


public class FindAllOrderAdjustmentAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAdjustmentAttribute> returnVal = new ArrayList<OrderAdjustmentAttribute>();
try{
List<GenericValue> results = delegator.findAll("OrderAdjustmentAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderAdjustmentAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderAdjustmentAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
