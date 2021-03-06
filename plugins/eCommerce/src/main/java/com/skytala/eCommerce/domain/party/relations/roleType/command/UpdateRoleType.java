package com.skytala.eCommerce.domain.party.relations.roleType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.roleType.event.RoleTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.roleType.model.RoleType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRoleType extends Command {

private RoleType elementToBeUpdated;

public UpdateRoleType(RoleType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RoleType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RoleType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RoleType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RoleType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RoleType.class);
}
success = false;
}
Event resultingEvent = new RoleTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
