package com.skytala.eCommerce.domain.product.relations.productStoreFacility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.event.ProductStoreFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.mapper.ProductStoreFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.model.ProductStoreFacility;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductStoreFacility extends Command {

private ProductStoreFacility elementToBeAdded;
public AddProductStoreFacility(ProductStoreFacility elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductStoreFacility addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductStoreFacility", elementToBeAdded.mapAttributeField());
addedElement = ProductStoreFacilityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductStoreFacilityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
