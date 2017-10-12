package com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.event.GlAccountGroupMemberUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroupMember.model.GlAccountGroupMember;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountGroupMember extends Command {

private GlAccountGroupMember elementToBeUpdated;

public UpdateGlAccountGroupMember(GlAccountGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountGroupMember getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountGroupMember", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountGroupMember.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountGroupMember.class);
}
success = false;
}
Event resultingEvent = new GlAccountGroupMemberUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
