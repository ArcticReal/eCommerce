package com.skytala.eCommerce.domain.dataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.dataResource.event.DataResourceAdded;
import com.skytala.eCommerce.domain.dataResource.mapper.DataResourceMapper;
import com.skytala.eCommerce.domain.dataResource.model.DataResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResource extends Command {

private DataResource elementToBeAdded;
public AddDataResource(DataResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResource addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDataResourceId(delegator.getNextSeqId("DataResource"));
GenericValue newValue = delegator.makeValue("DataResource", elementToBeAdded.mapAttributeField());
addedElement = DataResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
