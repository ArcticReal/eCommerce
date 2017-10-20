package com.skytala.eCommerce.domain.product.relations.product.command.assocType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.assocType.ProductAssocTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.assocType.ProductAssocType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductAssocType extends Command {

private ProductAssocType elementToBeUpdated;

public UpdateProductAssocType(ProductAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductAssocType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductAssocType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductAssocType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductAssocType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductAssocType.class);
}
success = false;
}
Event resultingEvent = new ProductAssocTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
