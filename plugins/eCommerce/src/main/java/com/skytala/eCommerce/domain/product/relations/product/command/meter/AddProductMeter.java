package com.skytala.eCommerce.domain.product.relations.product.command.meter;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.meter.ProductMeterAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.meter.ProductMeterMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.meter.ProductMeter;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductMeter extends Command {

private ProductMeter elementToBeAdded;
public AddProductMeter(ProductMeter elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductMeter addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductMeter", elementToBeAdded.mapAttributeField());
addedElement = ProductMeterMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductMeterAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
