package com.skytala.eCommerce.domain.party.relations.validContactMechRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.validContactMechRole.event.ValidContactMechRoleUpdated;
import com.skytala.eCommerce.domain.party.relations.validContactMechRole.model.ValidContactMechRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateValidContactMechRole extends Command {

private ValidContactMechRole elementToBeUpdated;

public UpdateValidContactMechRole(ValidContactMechRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ValidContactMechRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ValidContactMechRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ValidContactMechRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ValidContactMechRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ValidContactMechRole.class);
}
success = false;
}
Event resultingEvent = new ValidContactMechRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
