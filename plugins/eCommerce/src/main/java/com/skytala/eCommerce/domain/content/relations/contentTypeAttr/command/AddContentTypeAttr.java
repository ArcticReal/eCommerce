package com.skytala.eCommerce.domain.content.relations.contentTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentTypeAttr.event.ContentTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.contentTypeAttr.mapper.ContentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.contentTypeAttr.model.ContentTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentTypeAttr extends Command {

private ContentTypeAttr elementToBeAdded;
public AddContentTypeAttr(ContentTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = ContentTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
