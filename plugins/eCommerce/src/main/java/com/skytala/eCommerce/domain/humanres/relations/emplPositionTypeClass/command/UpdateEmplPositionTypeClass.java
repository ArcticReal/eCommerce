package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.event.EmplPositionTypeClassUpdated;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model.EmplPositionTypeClass;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmplPositionTypeClass extends Command {

private EmplPositionTypeClass elementToBeUpdated;

public UpdateEmplPositionTypeClass(EmplPositionTypeClass elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmplPositionTypeClass getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmplPositionTypeClass elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmplPositionTypeClass", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmplPositionTypeClass.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmplPositionTypeClass.class);
}
success = false;
}
Event resultingEvent = new EmplPositionTypeClassUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
