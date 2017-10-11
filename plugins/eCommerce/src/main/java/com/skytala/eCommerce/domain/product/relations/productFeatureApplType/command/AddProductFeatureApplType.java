package com.skytala.eCommerce.domain.product.relations.productFeatureApplType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.event.ProductFeatureApplTypeAdded;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.mapper.ProductFeatureApplTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.model.ProductFeatureApplType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureApplType extends Command {

private ProductFeatureApplType elementToBeAdded;
public AddProductFeatureApplType(ProductFeatureApplType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureApplType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureApplTypeId(delegator.getNextSeqId("ProductFeatureApplType"));
GenericValue newValue = delegator.makeValue("ProductFeatureApplType", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureApplTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureApplTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
