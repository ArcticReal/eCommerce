package com.skytala.eCommerce.domain.content.relations.dataResource.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.type.DataResourceTypeAdded;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.type.DataResourceTypeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.type.DataResourceType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResourceType extends Command {

private DataResourceType elementToBeAdded;
public AddDataResourceType(DataResourceType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResourceType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDataResourceTypeId(delegator.getNextSeqId("DataResourceType"));
GenericValue newValue = delegator.makeValue("DataResourceType", elementToBeAdded.mapAttributeField());
addedElement = DataResourceTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourceTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
