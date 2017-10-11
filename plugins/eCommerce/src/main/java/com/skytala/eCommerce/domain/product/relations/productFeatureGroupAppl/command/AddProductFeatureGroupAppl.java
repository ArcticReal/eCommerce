package com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.event.ProductFeatureGroupApplAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.mapper.ProductFeatureGroupApplMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.model.ProductFeatureGroupAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureGroupAppl extends Command {

private ProductFeatureGroupAppl elementToBeAdded;
public AddProductFeatureGroupAppl(ProductFeatureGroupAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureGroupAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFeatureGroupAppl", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureGroupApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureGroupApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
