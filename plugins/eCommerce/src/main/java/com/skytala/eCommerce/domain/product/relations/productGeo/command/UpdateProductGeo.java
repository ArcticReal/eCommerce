package com.skytala.eCommerce.domain.product.relations.productGeo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productGeo.event.ProductGeoUpdated;
import com.skytala.eCommerce.domain.product.relations.productGeo.model.ProductGeo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductGeo extends Command {

private ProductGeo elementToBeUpdated;

public UpdateProductGeo(ProductGeo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductGeo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductGeo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductGeo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductGeo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductGeo.class);
}
success = false;
}
Event resultingEvent = new ProductGeoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
