package com.skytala.eCommerce.domain.product.relations.product.command.meterType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.meterType.ProductMeterTypeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.meterType.ProductMeterTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.meterType.ProductMeterType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductMeterType extends Command {

private ProductMeterType elementToBeAdded;
public AddProductMeterType(ProductMeterType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductMeterType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setProductMeterTypeId(delegator.getNextSeqId("ProductMeterType"));
GenericValue newValue = delegator.makeValue("ProductMeterType", elementToBeAdded.mapAttributeField());
addedElement = ProductMeterTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductMeterTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
