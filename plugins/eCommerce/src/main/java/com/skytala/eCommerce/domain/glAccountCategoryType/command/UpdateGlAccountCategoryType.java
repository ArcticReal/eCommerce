package com.skytala.eCommerce.domain.glAccountCategoryType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.glAccountCategoryType.event.GlAccountCategoryTypeUpdated;
import com.skytala.eCommerce.domain.glAccountCategoryType.model.GlAccountCategoryType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountCategoryType extends Command {

private GlAccountCategoryType elementToBeUpdated;

public UpdateGlAccountCategoryType(GlAccountCategoryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountCategoryType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountCategoryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountCategoryType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountCategoryType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountCategoryType.class);
}
success = false;
}
Event resultingEvent = new GlAccountCategoryTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
