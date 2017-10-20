package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetAssign;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetAssign.WorkEffortFixedAssetAssignAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetAssign.WorkEffortFixedAssetAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetAssign.WorkEffortFixedAssetAssign;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortFixedAssetAssign extends Command {

private WorkEffortFixedAssetAssign elementToBeAdded;
public AddWorkEffortFixedAssetAssign(WorkEffortFixedAssetAssign elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortFixedAssetAssign addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortFixedAssetAssign", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortFixedAssetAssignMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortFixedAssetAssignAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
