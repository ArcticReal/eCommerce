package com.skytala.eCommerce.domain.glXbrlClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassUpdated;
import com.skytala.eCommerce.domain.glXbrlClass.model.GlXbrlClass;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlXbrlClass extends Command {

private GlXbrlClass elementToBeUpdated;

public UpdateGlXbrlClass(GlXbrlClass elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlXbrlClass getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlXbrlClass elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlXbrlClass", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlXbrlClass.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlXbrlClass.class);
}
success = false;
}
Event resultingEvent = new GlXbrlClassUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
