package com.skytala.eCommerce.domain.content.relations.webSiteContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.event.WebSiteContentAdded;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.mapper.WebSiteContentMapper;
import com.skytala.eCommerce.domain.content.relations.webSiteContent.model.WebSiteContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebSiteContent extends Command {

private WebSiteContent elementToBeAdded;
public AddWebSiteContent(WebSiteContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebSiteContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WebSiteContent", elementToBeAdded.mapAttributeField());
addedElement = WebSiteContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebSiteContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
