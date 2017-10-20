package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType.WorkEffortAssocTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocType.WorkEffortAssocTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortAssocType extends Command {

private WorkEffortAssocType elementToBeAdded;
public AddWorkEffortAssocType(WorkEffortAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkEffortAssocTypeId(delegator.getNextSeqId("WorkEffortAssocType"));
GenericValue newValue = delegator.makeValue("WorkEffortAssocType", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
