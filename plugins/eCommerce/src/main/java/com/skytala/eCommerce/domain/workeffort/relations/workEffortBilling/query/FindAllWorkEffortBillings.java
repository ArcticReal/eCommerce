
package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.event.WorkEffortBillingFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.mapper.WorkEffortBillingMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model.WorkEffortBilling;


public class FindAllWorkEffortBillings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortBilling> returnVal = new ArrayList<WorkEffortBilling>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortBilling", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortBillingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortBillingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
