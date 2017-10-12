package com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.event.DataResourceTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.mapper.DataResourceTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.model.DataResourceTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResourceTypeAttr extends Command {

private DataResourceTypeAttr elementToBeAdded;
public AddDataResourceTypeAttr(DataResourceTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResourceTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("DataResourceTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = DataResourceTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourceTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
