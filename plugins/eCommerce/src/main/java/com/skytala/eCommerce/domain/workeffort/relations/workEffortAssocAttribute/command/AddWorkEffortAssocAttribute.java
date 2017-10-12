package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.event.WorkEffortAssocAttributeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.mapper.WorkEffortAssocAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssocAttribute.model.WorkEffortAssocAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortAssocAttribute extends Command {

private WorkEffortAssocAttribute elementToBeAdded;
public AddWorkEffortAssocAttribute(WorkEffortAssocAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortAssocAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortAssocAttribute", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortAssocAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortAssocAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
