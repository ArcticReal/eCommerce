
package com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.event.WorkOrderItemFulfillmentFound;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.mapper.WorkOrderItemFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.workOrderItemFulfillment.model.WorkOrderItemFulfillment;


public class FindAllWorkOrderItemFulfillments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkOrderItemFulfillment> returnVal = new ArrayList<WorkOrderItemFulfillment>();
try{
List<GenericValue> results = delegator.findAll("WorkOrderItemFulfillment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkOrderItemFulfillmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkOrderItemFulfillmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
