package com.skytala.eCommerce.domain.content.relations.content.command.webSite;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.content.event.webSite.WebSiteContentUpdated;
import com.skytala.eCommerce.domain.content.relations.content.model.webSite.WebSiteContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebSiteContent extends Command {

private WebSiteContent elementToBeUpdated;

public UpdateWebSiteContent(WebSiteContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebSiteContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebSiteContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebSiteContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebSiteContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebSiteContent.class);
}
success = false;
}
Event resultingEvent = new WebSiteContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
