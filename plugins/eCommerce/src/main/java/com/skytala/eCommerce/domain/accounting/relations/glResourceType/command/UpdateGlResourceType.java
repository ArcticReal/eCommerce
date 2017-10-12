package com.skytala.eCommerce.domain.accounting.relations.glResourceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.event.GlResourceTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlResourceType extends Command {

private GlResourceType elementToBeUpdated;

public UpdateGlResourceType(GlResourceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlResourceType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlResourceType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlResourceType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlResourceType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlResourceType.class);
}
success = false;
}
Event resultingEvent = new GlResourceTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
