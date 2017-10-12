package com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.event.SalesForecastHistoryUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastHistory.model.SalesForecastHistory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesForecastHistory extends Command {

private SalesForecastHistory elementToBeUpdated;

public UpdateSalesForecastHistory(SalesForecastHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesForecastHistory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesForecastHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesForecastHistory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesForecastHistory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesForecastHistory.class);
}
success = false;
}
Event resultingEvent = new SalesForecastHistoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
