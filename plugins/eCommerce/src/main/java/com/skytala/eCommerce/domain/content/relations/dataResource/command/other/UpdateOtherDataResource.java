package com.skytala.eCommerce.domain.content.relations.dataResource.command.other;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.other.OtherDataResourceUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.other.OtherDataResource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOtherDataResource extends Command {

private OtherDataResource elementToBeUpdated;

public UpdateOtherDataResource(OtherDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OtherDataResource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OtherDataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OtherDataResource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OtherDataResource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OtherDataResource.class);
}
success = false;
}
Event resultingEvent = new OtherDataResourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
