package com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.event.DataResourceAttributeAdded;
import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.mapper.DataResourceAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.model.DataResourceAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResourceAttribute extends Command {

private DataResourceAttribute elementToBeAdded;
public AddDataResourceAttribute(DataResourceAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResourceAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("DataResourceAttribute", elementToBeAdded.mapAttributeField());
addedElement = DataResourceAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourceAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
