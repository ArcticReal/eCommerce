package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event.WebSitePathAliasUpdated;
import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWebSitePathAlias extends Command {

private WebSitePathAlias elementToBeUpdated;

public UpdateWebSitePathAlias(WebSitePathAlias elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WebSitePathAlias getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WebSitePathAlias elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WebSitePathAlias", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WebSitePathAlias.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WebSitePathAlias.class);
}
success = false;
}
Event resultingEvent = new WebSitePathAliasUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
