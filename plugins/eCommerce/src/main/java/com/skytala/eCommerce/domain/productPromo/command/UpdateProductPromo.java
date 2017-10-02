package com.skytala.eCommerce.domain.productPromo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.productPromo.event.ProductPromoUpdated;
import com.skytala.eCommerce.domain.productPromo.model.ProductPromo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductPromo extends Command {

private ProductPromo elementToBeUpdated;

public UpdateProductPromo(ProductPromo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductPromo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductPromo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductPromo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductPromo.class); 
}
success = true;
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}
Broker.instance().publish(new ProductPromoUpdated(success));
return null;
}
}