package com.skytala.eCommerce.domain.product.relations.marketInterest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestUpdated;
import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMarketInterest extends Command {

private MarketInterest elementToBeUpdated;

public UpdateMarketInterest(MarketInterest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MarketInterest getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MarketInterest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MarketInterest", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MarketInterest.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MarketInterest.class);
}
success = false;
}
Event resultingEvent = new MarketInterestUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
