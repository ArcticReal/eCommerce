package com.skytala.eCommerce.domain.content.relations.content.command.purposeType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.purposeType.ContentPurposeTypeUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.purposeType.ContentPurposeType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentPurposeType extends Command {

private ContentPurposeType elementToBeUpdated;

public UpdateContentPurposeType(ContentPurposeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentPurposeType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentPurposeType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentPurposeType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentPurposeType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentPurposeType.class);
}
success = false;
}
Event resultingEvent = new ContentPurposeTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
