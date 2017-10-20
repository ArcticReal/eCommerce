package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.type.GlAccountTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.type.GlAccountType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountType extends Command {

private GlAccountType elementToBeUpdated;

public UpdateGlAccountType(GlAccountType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountType.class);
}
success = false;
}
Event resultingEvent = new GlAccountTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
