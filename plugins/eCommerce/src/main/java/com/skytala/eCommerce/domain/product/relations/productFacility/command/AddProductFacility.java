package com.skytala.eCommerce.domain.product.relations.productFacility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFacility.event.ProductFacilityAdded;
import com.skytala.eCommerce.domain.product.relations.productFacility.mapper.ProductFacilityMapper;
import com.skytala.eCommerce.domain.product.relations.productFacility.model.ProductFacility;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFacility extends Command {

private ProductFacility elementToBeAdded;
public AddProductFacility(ProductFacility elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFacility addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductFacility", elementToBeAdded.mapAttributeField());
addedElement = ProductFacilityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFacilityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
