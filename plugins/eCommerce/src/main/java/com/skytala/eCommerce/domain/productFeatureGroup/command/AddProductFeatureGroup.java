package com.skytala.eCommerce.domain.productFeatureGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.productFeatureGroup.event.ProductFeatureGroupAdded;
import com.skytala.eCommerce.domain.productFeatureGroup.mapper.ProductFeatureGroupMapper;
import com.skytala.eCommerce.domain.productFeatureGroup.model.ProductFeatureGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFeatureGroup extends Command {

private ProductFeatureGroup elementToBeAdded;
public AddProductFeatureGroup(ProductFeatureGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFeatureGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductFeatureGroupId(delegator.getNextSeqId("ProductFeatureGroup"));
GenericValue newValue = delegator.makeValue("ProductFeatureGroup", elementToBeAdded.mapAttributeField());
addedElement = ProductFeatureGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFeatureGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
