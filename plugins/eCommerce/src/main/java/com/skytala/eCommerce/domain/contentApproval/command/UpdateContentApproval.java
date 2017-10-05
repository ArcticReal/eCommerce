package com.skytala.eCommerce.domain.contentApproval.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.contentApproval.event.ContentApprovalUpdated;
import com.skytala.eCommerce.domain.contentApproval.model.ContentApproval;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentApproval extends Command {

private ContentApproval elementToBeUpdated;

public UpdateContentApproval(ContentApproval elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentApproval getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentApproval elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentApproval", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentApproval.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentApproval.class);
}
success = false;
}
Event resultingEvent = new ContentApprovalUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
