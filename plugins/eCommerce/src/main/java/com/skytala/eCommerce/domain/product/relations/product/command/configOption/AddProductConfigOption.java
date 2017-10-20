package com.skytala.eCommerce.domain.product.relations.product.command.configOption;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.configOption.ProductConfigOptionAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOption.ProductConfigOptionMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOption.ProductConfigOption;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfigOption extends Command {

private ProductConfigOption elementToBeAdded;
public AddProductConfigOption(ProductConfigOption elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfigOption addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConfigOptionId(delegator.getNextSeqId("ProductConfigOption"));
GenericValue newValue = delegator.makeValue("ProductConfigOption", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigOptionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigOptionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
