package com.skytala.eCommerce.domain.product.relations.productFeature.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeature.event.ProductFeatureAdded;
import com.skytala.eCommerce.domain.product.relations.productFeature.mapper.ProductFeatureMapper;
import com.skytala.eCommerce.domain.product.relations.productFeature.model.ProductFeature;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeature extends Command {

private ProductFeature elementToBeAdded;
public AddProductFeature(ProductFeature elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeature addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureId(delegator.getNextSeqId("ProductFeature"));
GenericValue newValue = delegator.makeValue("ProductFeature", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
