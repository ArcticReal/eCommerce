package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event.WorkEffortFixedAssetAssignUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model.WorkEffortFixedAssetAssign;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortFixedAssetAssign extends Command {

private WorkEffortFixedAssetAssign elementToBeUpdated;

public UpdateWorkEffortFixedAssetAssign(WorkEffortFixedAssetAssign elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortFixedAssetAssign getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortFixedAssetAssign elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortFixedAssetAssign", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortFixedAssetAssign.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortFixedAssetAssign.class);
}
success = false;
}
Event resultingEvent = new WorkEffortFixedAssetAssignUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
