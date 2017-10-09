package com.skytala.eCommerce.domain.salesForecastDetail.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.salesForecastDetail.event.SalesForecastDetailUpdated;
import com.skytala.eCommerce.domain.salesForecastDetail.model.SalesForecastDetail;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesForecastDetail extends Command {

private SalesForecastDetail elementToBeUpdated;

public UpdateSalesForecastDetail(SalesForecastDetail elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesForecastDetail getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesForecastDetail elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesForecastDetail", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesForecastDetail.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesForecastDetail.class);
}
success = false;
}
Event resultingEvent = new SalesForecastDetailUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}