package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.assocTypeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocTypeAttr.WorkEffortAssocTypeAttrAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocTypeAttr.WorkEffortAssocTypeAttrMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocTypeAttr.WorkEffortAssocTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortAssocTypeAttr extends Command {

private WorkEffortAssocTypeAttr elementToBeAdded;
public AddWorkEffortAssocTypeAttr(WorkEffortAssocTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortAssocTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortAssocTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortAssocTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortAssocTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
