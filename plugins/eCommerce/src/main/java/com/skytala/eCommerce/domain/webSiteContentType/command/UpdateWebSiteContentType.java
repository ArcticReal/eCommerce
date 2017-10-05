package com.skytala.eCommerce.domain.webSiteContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.webSiteContentType.event.WebSiteContentTypeUpdated;
import com.skytala.eCommerce.domain.webSiteContentType.model.WebSiteContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebSiteContentType extends Command {

private WebSiteContentType elementToBeUpdated;

public UpdateWebSiteContentType(WebSiteContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebSiteContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebSiteContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebSiteContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebSiteContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebSiteContentType.class);
}
success = false;
}
Event resultingEvent = new WebSiteContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
