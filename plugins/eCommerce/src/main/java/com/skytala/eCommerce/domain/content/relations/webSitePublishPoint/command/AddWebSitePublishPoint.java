package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event.WebSitePublishPointAdded;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.mapper.WebSitePublishPointMapper;
import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebSitePublishPoint extends Command {

private WebSitePublishPoint elementToBeAdded;
public AddWebSitePublishPoint(WebSitePublishPoint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebSitePublishPoint addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WebSitePublishPoint", elementToBeAdded.mapAttributeField());
addedElement = WebSitePublishPointMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebSitePublishPointAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
