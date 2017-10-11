package com.skytala.eCommerce.domain.product.relations.productAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productAssoc.event.ProductAssocUpdated;
import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductAssoc extends Command {

private ProductAssoc elementToBeUpdated;

public UpdateProductAssoc(ProductAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductAssoc.class);
}
success = false;
}
Event resultingEvent = new ProductAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
