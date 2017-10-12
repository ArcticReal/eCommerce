package com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.event.DataResourcePurposeAdded;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.mapper.DataResourcePurposeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourcePurpose.model.DataResourcePurpose;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResourcePurpose extends Command {

private DataResourcePurpose elementToBeAdded;
public AddDataResourcePurpose(DataResourcePurpose elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResourcePurpose addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("DataResourcePurpose", elementToBeAdded.mapAttributeField());
addedElement = DataResourcePurposeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourcePurposeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
