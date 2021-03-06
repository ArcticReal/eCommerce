package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.groupType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupType.GlAccountGroupTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupType.GlAccountGroupType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountGroupType extends Command {

private GlAccountGroupType elementToBeUpdated;

public UpdateGlAccountGroupType(GlAccountGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountGroupType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountGroupType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountGroupType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountGroupType.class);
}
success = false;
}
Event resultingEvent = new GlAccountGroupTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
