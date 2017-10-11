package com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.event.ProdConfItemContentTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContentType.model.ProdConfItemContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProdConfItemContentType extends Command {

private ProdConfItemContentType elementToBeUpdated;

public UpdateProdConfItemContentType(ProdConfItemContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProdConfItemContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProdConfItemContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProdConfItemContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProdConfItemContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProdConfItemContentType.class);
}
success = false;
}
Event resultingEvent = new ProdConfItemContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
