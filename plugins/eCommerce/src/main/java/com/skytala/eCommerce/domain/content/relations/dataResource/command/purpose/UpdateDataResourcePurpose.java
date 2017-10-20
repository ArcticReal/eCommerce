package com.skytala.eCommerce.domain.content.relations.dataResource.command.purpose;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose.DataResourcePurposeUpdated;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.purpose.DataResourcePurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataResourcePurpose extends Command {

private DataResourcePurpose elementToBeUpdated;

public UpdateDataResourcePurpose(DataResourcePurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataResourcePurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataResourcePurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataResourcePurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataResourcePurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataResourcePurpose.class);
}
success = false;
}
Event resultingEvent = new DataResourcePurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
