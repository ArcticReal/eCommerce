package com.skytala.eCommerce.domain.fileExtension.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.fileExtension.event.FileExtensionAdded;
import com.skytala.eCommerce.domain.fileExtension.mapper.FileExtensionMapper;
import com.skytala.eCommerce.domain.fileExtension.model.FileExtension;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFileExtension extends Command {

private FileExtension elementToBeAdded;
public AddFileExtension(FileExtension elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FileExtension addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFileExtensionId(delegator.getNextSeqId("FileExtension"));
GenericValue newValue = delegator.makeValue("FileExtension", elementToBeAdded.mapAttributeField());
addedElement = FileExtensionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FileExtensionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
