package com.skytala.eCommerce.domain.product.relations.productFeatureAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.event.ProductFeatureApplAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.mapper.ProductFeatureApplMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.model.ProductFeatureAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureAppl extends Command {

private ProductFeatureAppl elementToBeAdded;
public AddProductFeatureAppl(ProductFeatureAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFeatureAppl", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
