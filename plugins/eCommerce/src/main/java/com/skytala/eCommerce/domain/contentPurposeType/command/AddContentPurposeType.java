package com.skytala.eCommerce.domain.contentPurposeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contentPurposeType.event.ContentPurposeTypeAdded;
import com.skytala.eCommerce.domain.contentPurposeType.mapper.ContentPurposeTypeMapper;
import com.skytala.eCommerce.domain.contentPurposeType.model.ContentPurposeType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentPurposeType extends Command {

private ContentPurposeType elementToBeAdded;
public AddContentPurposeType(ContentPurposeType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentPurposeType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentPurposeTypeId(delegator.getNextSeqId("ContentPurposeType"));
GenericValue newValue = delegator.makeValue("ContentPurposeType", elementToBeAdded.mapAttributeField());
addedElement = ContentPurposeTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentPurposeTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
