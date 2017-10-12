package com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.event.MrpEventTypeUpdated;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.model.MrpEventType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMrpEventType extends Command {

private MrpEventType elementToBeUpdated;

public UpdateMrpEventType(MrpEventType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MrpEventType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MrpEventType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MrpEventType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MrpEventType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MrpEventType.class);
}
success = false;
}
Event resultingEvent = new MrpEventTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
