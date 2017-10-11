package com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.event.ProductFeatureDataResourceAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.mapper.ProductFeatureDataResourceMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.model.ProductFeatureDataResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureDataResource extends Command {

private ProductFeatureDataResource elementToBeAdded;
public AddProductFeatureDataResource(ProductFeatureDataResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureDataResource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFeatureDataResource", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureDataResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureDataResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
