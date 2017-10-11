package com.skytala.eCommerce.domain.product.relations.productGeo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productGeo.event.ProductGeoAdded;
import com.skytala.eCommerce.domain.product.relations.productGeo.mapper.ProductGeoMapper;
import com.skytala.eCommerce.domain.product.relations.productGeo.model.ProductGeo;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductGeo extends Command {

private ProductGeo elementToBeAdded;
public AddProductGeo(ProductGeo elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductGeo addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductGeo", elementToBeAdded.mapAttributeField());
addedElement = ProductGeoMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductGeoAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
