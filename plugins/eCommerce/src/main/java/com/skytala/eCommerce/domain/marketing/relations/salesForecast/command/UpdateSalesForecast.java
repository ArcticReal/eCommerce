package com.skytala.eCommerce.domain.marketing.relations.salesForecast.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.SalesForecastUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.SalesForecast;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesForecast extends Command {

private SalesForecast elementToBeUpdated;

public UpdateSalesForecast(SalesForecast elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesForecast getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesForecast elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesForecast", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesForecast.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesForecast.class);
}
success = false;
}
Event resultingEvent = new SalesForecastUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
