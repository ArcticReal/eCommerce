package com.skytala.eCommerce.domain.product.relations.productMaintType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productMaintType.event.ProductMaintTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productMaintType.model.ProductMaintType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductMaintType extends Command {

private ProductMaintType elementToBeUpdated;

public UpdateProductMaintType(ProductMaintType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductMaintType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductMaintType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductMaintType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductMaintType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductMaintType.class);
}
success = false;
}
Event resultingEvent = new ProductMaintTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
