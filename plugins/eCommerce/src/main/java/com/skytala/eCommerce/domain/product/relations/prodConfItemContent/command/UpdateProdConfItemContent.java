package com.skytala.eCommerce.domain.product.relations.prodConfItemContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.event.ProdConfItemContentUpdated;
import com.skytala.eCommerce.domain.product.relations.prodConfItemContent.model.ProdConfItemContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProdConfItemContent extends Command {

private ProdConfItemContent elementToBeUpdated;

public UpdateProdConfItemContent(ProdConfItemContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProdConfItemContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProdConfItemContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProdConfItemContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProdConfItemContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProdConfItemContent.class);
}
success = false;
}
Event resultingEvent = new ProdConfItemContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
