
package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.event.WorkEffortSearchConstraintFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.mapper.WorkEffortSearchConstraintMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchConstraint.model.WorkEffortSearchConstraint;


public class FindAllWorkEffortSearchConstraints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortSearchConstraint> returnVal = new ArrayList<WorkEffortSearchConstraint>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortSearchConstraint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortSearchConstraintMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortSearchConstraintFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
