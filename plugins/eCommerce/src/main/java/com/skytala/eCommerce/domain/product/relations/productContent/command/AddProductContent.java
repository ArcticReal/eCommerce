package com.skytala.eCommerce.domain.product.relations.productContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productContent.event.ProductContentAdded;
import com.skytala.eCommerce.domain.product.relations.productContent.mapper.ProductContentMapper;
import com.skytala.eCommerce.domain.product.relations.productContent.model.ProductContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductContent extends Command {

private ProductContent elementToBeAdded;
public AddProductContent(ProductContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductContent", elementToBeAdded.mapAttributeField());
addedElement = ProductContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
