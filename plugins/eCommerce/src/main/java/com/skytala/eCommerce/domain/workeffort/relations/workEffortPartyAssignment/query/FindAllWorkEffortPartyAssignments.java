
package com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.event.WorkEffortPartyAssignmentFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.mapper.WorkEffortPartyAssignmentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortPartyAssignment.model.WorkEffortPartyAssignment;


public class FindAllWorkEffortPartyAssignments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortPartyAssignment> returnVal = new ArrayList<WorkEffortPartyAssignment>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortPartyAssignment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortPartyAssignmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortPartyAssignmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
