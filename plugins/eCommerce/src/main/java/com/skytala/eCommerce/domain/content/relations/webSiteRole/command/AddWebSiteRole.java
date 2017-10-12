package com.skytala.eCommerce.domain.content.relations.webSiteRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.event.WebSiteRoleAdded;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.mapper.WebSiteRoleMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebSiteRole extends Command {

private WebSiteRole elementToBeAdded;
public AddWebSiteRole(WebSiteRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebSiteRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WebSiteRole", elementToBeAdded.mapAttributeField());
addedElement = WebSiteRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebSiteRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
