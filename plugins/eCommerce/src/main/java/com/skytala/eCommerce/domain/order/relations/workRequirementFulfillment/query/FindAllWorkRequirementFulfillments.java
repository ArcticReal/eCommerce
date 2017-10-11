
package com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.event.WorkRequirementFulfillmentFound;
import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.mapper.WorkRequirementFulfillmentMapper;
import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.model.WorkRequirementFulfillment;


public class FindAllWorkRequirementFulfillments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkRequirementFulfillment> returnVal = new ArrayList<WorkRequirementFulfillment>();
try{
List<GenericValue> results = delegator.findAll("WorkRequirementFulfillment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkRequirementFulfillmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkRequirementFulfillmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
