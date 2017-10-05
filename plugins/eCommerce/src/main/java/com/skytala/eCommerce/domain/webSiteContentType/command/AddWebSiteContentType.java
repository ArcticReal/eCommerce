package com.skytala.eCommerce.domain.webSiteContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.webSiteContentType.event.WebSiteContentTypeAdded;
import com.skytala.eCommerce.domain.webSiteContentType.mapper.WebSiteContentTypeMapper;
import com.skytala.eCommerce.domain.webSiteContentType.model.WebSiteContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebSiteContentType extends Command {

private WebSiteContentType elementToBeAdded;
public AddWebSiteContentType(WebSiteContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebSiteContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWebSiteContentTypeId(delegator.getNextSeqId("WebSiteContentType"));
GenericValue newValue = delegator.makeValue("WebSiteContentType", elementToBeAdded.mapAttributeField());
addedElement = WebSiteContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebSiteContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
