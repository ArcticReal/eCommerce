package com.skytala.eCommerce.domain.dataResourceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.dataResourceType.event.DataResourceTypeUpdated;
import com.skytala.eCommerce.domain.dataResourceType.model.DataResourceType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDataResourceType extends Command {

private DataResourceType elementToBeUpdated;

public UpdateDataResourceType(DataResourceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public DataResourceType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(DataResourceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("DataResourceType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(DataResourceType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(DataResourceType.class);
}
success = false;
}
Event resultingEvent = new DataResourceTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
