package com.skytala.eCommerce.domain.content.relations.content.command.searchResult;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.searchResult.ContentSearchResultUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.searchResult.ContentSearchResult;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContentSearchResult extends Command {

private ContentSearchResult elementToBeUpdated;

public UpdateContentSearchResult(ContentSearchResult elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContentSearchResult getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContentSearchResult elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContentSearchResult", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContentSearchResult.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContentSearchResult.class);
}
success = false;
}
Event resultingEvent = new ContentSearchResultUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
