package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.partyAssignment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.partyAssignment.WorkEffortPartyAssignmentAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.partyAssignment.WorkEffortPartyAssignmentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.partyAssignment.WorkEffortPartyAssignment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortPartyAssignment extends Command {

private WorkEffortPartyAssignment elementToBeAdded;
public AddWorkEffortPartyAssignment(WorkEffortPartyAssignment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortPartyAssignment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("WorkEffortPartyAssignment"));
GenericValue newValue = delegator.makeValue("WorkEffortPartyAssignment", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortPartyAssignmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortPartyAssignmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
