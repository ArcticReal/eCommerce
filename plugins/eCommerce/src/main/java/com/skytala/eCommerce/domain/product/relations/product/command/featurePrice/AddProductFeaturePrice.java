package com.skytala.eCommerce.domain.product.relations.product.command.featurePrice;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.featurePrice.ProductFeaturePriceAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.featurePrice.ProductFeaturePriceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.featurePrice.ProductFeaturePrice;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeaturePrice extends Command {

private ProductFeaturePrice elementToBeAdded;
public AddProductFeaturePrice(ProductFeaturePrice elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeaturePrice addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureId(delegator.getNextSeqId("ProductFeaturePrice"));
GenericValue newValue = delegator.makeValue("ProductFeaturePrice", elementToBeAdded.mapAttributeField());
addedElement = ProductFeaturePriceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeaturePriceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
