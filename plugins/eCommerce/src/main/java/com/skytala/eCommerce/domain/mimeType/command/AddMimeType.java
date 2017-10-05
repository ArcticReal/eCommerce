package com.skytala.eCommerce.domain.mimeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.mimeType.event.MimeTypeAdded;
import com.skytala.eCommerce.domain.mimeType.mapper.MimeTypeMapper;
import com.skytala.eCommerce.domain.mimeType.model.MimeType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMimeType extends Command {

private MimeType elementToBeAdded;
public AddMimeType(MimeType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MimeType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMimeTypeId(delegator.getNextSeqId("MimeType"));
GenericValue newValue = delegator.makeValue("MimeType", elementToBeAdded.mapAttributeField());
addedElement = MimeTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MimeTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
