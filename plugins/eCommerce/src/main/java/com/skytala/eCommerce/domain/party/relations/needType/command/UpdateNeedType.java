package com.skytala.eCommerce.domain.party.relations.needType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.needType.event.NeedTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.needType.model.NeedType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateNeedType extends Command {

private NeedType elementToBeUpdated;

public UpdateNeedType(NeedType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public NeedType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(NeedType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("NeedType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(NeedType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(NeedType.class);
}
success = false;
}
Event resultingEvent = new NeedTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
