
package com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.event.WorkEffortDeliverableProdFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.mapper.WorkEffortDeliverableProdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.model.WorkEffortDeliverableProd;


public class FindAllWorkEffortDeliverableProds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortDeliverableProd> returnVal = new ArrayList<WorkEffortDeliverableProd>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortDeliverableProd", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortDeliverableProdMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortDeliverableProdFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
