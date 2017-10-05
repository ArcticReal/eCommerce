package com.skytala.eCommerce.domain.commContentAssocType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.commContentAssocType.event.CommContentAssocTypeUpdated;
import com.skytala.eCommerce.domain.commContentAssocType.model.CommContentAssocType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommContentAssocType extends Command {

private CommContentAssocType elementToBeUpdated;

public UpdateCommContentAssocType(CommContentAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommContentAssocType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommContentAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommContentAssocType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommContentAssocType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommContentAssocType.class);
}
success = false;
}
Event resultingEvent = new CommContentAssocTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
