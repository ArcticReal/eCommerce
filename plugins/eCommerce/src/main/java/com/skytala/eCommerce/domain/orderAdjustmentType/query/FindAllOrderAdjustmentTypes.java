
package com.skytala.eCommerce.domain.orderAdjustmentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.orderAdjustmentType.event.OrderAdjustmentTypeFound;
import com.skytala.eCommerce.domain.orderAdjustmentType.mapper.OrderAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.orderAdjustmentType.model.OrderAdjustmentType;


public class FindAllOrderAdjustmentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OrderAdjustmentType> returnVal = new ArrayList<OrderAdjustmentType>();
try{
List<GenericValue> results = delegator.findAll("OrderAdjustmentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OrderAdjustmentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OrderAdjustmentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
