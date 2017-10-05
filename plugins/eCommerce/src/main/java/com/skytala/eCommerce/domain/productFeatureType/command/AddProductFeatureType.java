package com.skytala.eCommerce.domain.productFeatureType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productFeatureType.event.ProductFeatureTypeAdded;
import com.skytala.eCommerce.domain.productFeatureType.mapper.ProductFeatureTypeMapper;
import com.skytala.eCommerce.domain.productFeatureType.model.ProductFeatureType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureType extends Command {

private ProductFeatureType elementToBeAdded;
public AddProductFeatureType(ProductFeatureType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureTypeId(delegator.getNextSeqId("ProductFeatureType"));
GenericValue newValue = delegator.makeValue("ProductFeatureType", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
