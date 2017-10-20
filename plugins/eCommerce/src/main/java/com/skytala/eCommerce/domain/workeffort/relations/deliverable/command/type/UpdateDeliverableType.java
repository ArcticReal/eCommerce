package com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type.DeliverableTypeUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDeliverableType extends Command {

private DeliverableType elementToBeUpdated;

public UpdateDeliverableType(DeliverableType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DeliverableType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DeliverableType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DeliverableType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DeliverableType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DeliverableType.class);
}
success = false;
}
Event resultingEvent = new DeliverableTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
