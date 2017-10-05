package com.skytala.eCommerce.domain.dataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.dataResource.event.DataResourceUpdated;
import com.skytala.eCommerce.domain.dataResource.model.DataResource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataResource extends Command {

private DataResource elementToBeUpdated;

public UpdateDataResource(DataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataResource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataResource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataResource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataResource.class);
}
success = false;
}
Event resultingEvent = new DataResourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
