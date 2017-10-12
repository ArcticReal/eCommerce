package com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.event.MrpEventUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEvent.model.MrpEvent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMrpEvent extends Command {

private MrpEvent elementToBeUpdated;

public UpdateMrpEvent(MrpEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MrpEvent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MrpEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MrpEvent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MrpEvent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MrpEvent.class);
}
success = false;
}
Event resultingEvent = new MrpEventUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
