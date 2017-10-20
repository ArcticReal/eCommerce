package com.skytala.eCommerce.domain.content.relations.content.command.keyword;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentKeyword extends Command {

private ContentKeyword elementToBeUpdated;

public UpdateContentKeyword(ContentKeyword elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentKeyword getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentKeyword elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentKeyword", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentKeyword.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentKeyword.class);
}
success = false;
}
Event resultingEvent = new ContentKeywordUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
