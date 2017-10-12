package com.skytala.eCommerce.domain.content.relations.webPreferenceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.event.WebPreferenceTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebPreferenceType extends Command {

private WebPreferenceType elementToBeUpdated;

public UpdateWebPreferenceType(WebPreferenceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebPreferenceType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebPreferenceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebPreferenceType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebPreferenceType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebPreferenceType.class);
}
success = false;
}
Event resultingEvent = new WebPreferenceTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
