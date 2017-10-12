package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event.WebAnalyticsConfigUpdated;
import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebAnalyticsConfig extends Command {

private WebAnalyticsConfig elementToBeUpdated;

public UpdateWebAnalyticsConfig(WebAnalyticsConfig elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebAnalyticsConfig getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebAnalyticsConfig elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebAnalyticsConfig", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebAnalyticsConfig.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebAnalyticsConfig.class);
}
success = false;
}
Event resultingEvent = new WebAnalyticsConfigUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
