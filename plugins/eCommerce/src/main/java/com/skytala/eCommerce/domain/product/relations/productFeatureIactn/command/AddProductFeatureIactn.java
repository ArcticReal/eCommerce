package com.skytala.eCommerce.domain.product.relations.productFeatureIactn.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event.ProductFeatureIactnAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.mapper.ProductFeatureIactnMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureIactn extends Command {

private ProductFeatureIactn elementToBeAdded;
public AddProductFeatureIactn(ProductFeatureIactn elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureIactn addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFeatureIactn", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureIactnMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureIactnAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
