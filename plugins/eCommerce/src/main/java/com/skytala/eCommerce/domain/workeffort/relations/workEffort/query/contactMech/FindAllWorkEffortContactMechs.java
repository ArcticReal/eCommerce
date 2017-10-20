
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.contactMech;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.contactMech.WorkEffortContactMechFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contactMech.WorkEffortContactMechMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;


public class FindAllWorkEffortContactMechs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortContactMech> returnVal = new ArrayList<WorkEffortContactMech>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortContactMech", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortContactMechMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortContactMechFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
