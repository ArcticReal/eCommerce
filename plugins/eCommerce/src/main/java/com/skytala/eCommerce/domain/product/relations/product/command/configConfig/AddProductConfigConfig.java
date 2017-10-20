package com.skytala.eCommerce.domain.product.relations.product.command.configConfig;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.configConfig.ProductConfigConfigAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.configConfig.ProductConfigConfigMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfigConfig extends Command {

private ProductConfigConfig elementToBeAdded;
public AddProductConfigConfig(ProductConfigConfig elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfigConfig addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductConfigConfig", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigConfigMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigConfigAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
