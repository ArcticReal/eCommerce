package com.skytala.eCommerce.domain.product.relations.productConfigItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.event.ProductConfigItemAdded;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.mapper.ProductConfigItemMapper;
import com.skytala.eCommerce.domain.product.relations.productConfigItem.model.ProductConfigItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductConfigItem extends Command {

private ProductConfigItem elementToBeAdded;
public AddProductConfigItem(ProductConfigItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductConfigItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConfigItemId(delegator.getNextSeqId("ProductConfigItem"));
GenericValue newValue = delegator.makeValue("ProductConfigItem", elementToBeAdded.mapAttributeField());
addedElement = ProductConfigItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductConfigItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
