package com.skytala.eCommerce.domain.content.relations.contentAssocType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentAssocType.event.ContentAssocTypeAdded;
import com.skytala.eCommerce.domain.content.relations.contentAssocType.mapper.ContentAssocTypeMapper;
import com.skytala.eCommerce.domain.content.relations.contentAssocType.model.ContentAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentAssocType extends Command {

private ContentAssocType elementToBeAdded;
public AddContentAssocType(ContentAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentAssocTypeId(delegator.getNextSeqId("ContentAssocType"));
GenericValue newValue = delegator.makeValue("ContentAssocType", elementToBeAdded.mapAttributeField());
addedElement = ContentAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
