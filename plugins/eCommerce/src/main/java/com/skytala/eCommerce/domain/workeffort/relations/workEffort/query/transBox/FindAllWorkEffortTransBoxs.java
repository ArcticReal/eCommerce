
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.transBox;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.transBox.WorkEffortTransBoxFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.transBox.WorkEffortTransBoxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;


public class FindAllWorkEffortTransBoxs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortTransBox> returnVal = new ArrayList<WorkEffortTransBox>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortTransBox", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortTransBoxMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortTransBoxFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
