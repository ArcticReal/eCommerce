package com.skytala.eCommerce.domain.product.relations.product.command.configOptionOption;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionOption.ConfigOptionProductOptionAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionOption.ConfigOptionProductOptionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionOption.ConfigOptionProductOption;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddConfigOptionProductOption extends Command {

private ConfigOptionProductOption elementToBeAdded;
public AddConfigOptionProductOption(ConfigOptionProductOption elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ConfigOptionProductOption addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ConfigOptionProductOption", elementToBeAdded.mapAttributeField());
addedElement = ConfigOptionProductOptionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ConfigOptionProductOptionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
