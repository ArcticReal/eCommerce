package com.skytala.eCommerce.domain.content.relations.electronicText.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.electronicText.event.ElectronicTextAdded;
import com.skytala.eCommerce.domain.content.relations.electronicText.mapper.ElectronicTextMapper;
import com.skytala.eCommerce.domain.content.relations.electronicText.model.ElectronicText;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddElectronicText extends Command {

private ElectronicText elementToBeAdded;
public AddElectronicText(ElectronicText elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ElectronicText addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ElectronicText", elementToBeAdded.mapAttributeField());
addedElement = ElectronicTextMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ElectronicTextAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
