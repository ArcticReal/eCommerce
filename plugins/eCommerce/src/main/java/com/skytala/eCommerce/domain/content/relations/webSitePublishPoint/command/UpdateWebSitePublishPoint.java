package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointUpdated;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebSitePublishPoint extends Command {

private WebSitePublishPoint elementToBeUpdated;

public UpdateWebSitePublishPoint(WebSitePublishPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebSitePublishPoint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebSitePublishPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebSitePublishPoint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebSitePublishPoint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebSitePublishPoint.class);
}
success = false;
}
Event resultingEvent = new WebSitePublishPointUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
