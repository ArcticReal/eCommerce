package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event.ProductFacilityLocationAdded;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.mapper.ProductFacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductFacilityLocation extends Command {

private ProductFacilityLocation elementToBeAdded;
public AddProductFacilityLocation(ProductFacilityLocation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductFacilityLocation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setLocationSeqId(delegator.getNextSeqId("ProductFacilityLocation"));
GenericValue newValue = delegator.makeValue("ProductFacilityLocation", elementToBeAdded.mapAttributeField());
addedElement = ProductFacilityLocationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductFacilityLocationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
