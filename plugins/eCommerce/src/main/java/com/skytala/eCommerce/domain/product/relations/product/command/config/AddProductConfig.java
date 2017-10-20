package com.skytala.eCommerce.domain.product.relations.product.command.config;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.config.ProductConfigAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.config.ProductConfigMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfig;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfig extends Command {

private ProductConfig elementToBeAdded;
public AddProductConfig(ProductConfig elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfig addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductConfig", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
