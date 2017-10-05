package com.skytala.eCommerce.domain.webAnalyticsType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.webAnalyticsType.event.WebAnalyticsTypeUpdated;
import com.skytala.eCommerce.domain.webAnalyticsType.model.WebAnalyticsType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebAnalyticsType extends Command {

private WebAnalyticsType elementToBeUpdated;

public UpdateWebAnalyticsType(WebAnalyticsType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebAnalyticsType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebAnalyticsType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebAnalyticsType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebAnalyticsType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebAnalyticsType.class);
}
success = false;
}
Event resultingEvent = new WebAnalyticsTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
