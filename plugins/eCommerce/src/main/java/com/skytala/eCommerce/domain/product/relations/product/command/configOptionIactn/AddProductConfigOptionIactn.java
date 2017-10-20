package com.skytala.eCommerce.domain.product.relations.product.command.configOptionIactn;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn.ProductConfigOptionIactnAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configOptionIactn.ProductConfigOptionIactnMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configOptionIactn.ProductConfigOptionIactn;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfigOptionIactn extends Command {

private ProductConfigOptionIactn elementToBeAdded;
public AddProductConfigOptionIactn(ProductConfigOptionIactn elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfigOptionIactn addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductConfigOptionIactn", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigOptionIactnMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigOptionIactnAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
