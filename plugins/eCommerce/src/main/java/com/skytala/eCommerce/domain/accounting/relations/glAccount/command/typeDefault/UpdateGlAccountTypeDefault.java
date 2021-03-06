package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.typeDefault;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.typeDefault.GlAccountTypeDefaultUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault.GlAccountTypeDefault;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountTypeDefault extends Command {

private GlAccountTypeDefault elementToBeUpdated;

public UpdateGlAccountTypeDefault(GlAccountTypeDefault elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountTypeDefault getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountTypeDefault elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountTypeDefault", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountTypeDefault.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountTypeDefault.class);
}
success = false;
}
Event resultingEvent = new GlAccountTypeDefaultUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
