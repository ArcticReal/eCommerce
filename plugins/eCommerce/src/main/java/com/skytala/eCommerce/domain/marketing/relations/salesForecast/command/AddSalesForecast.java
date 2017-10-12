package com.skytala.eCommerce.domain.marketing.relations.salesForecast.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.SalesForecastAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.SalesForecastMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.SalesForecast;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesForecast extends Command {

private SalesForecast elementToBeAdded;
public AddSalesForecast(SalesForecast elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesForecast addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSalesForecastId(delegator.getNextSeqId("SalesForecast"));
GenericValue newValue = delegator.makeValue("SalesForecast", elementToBeAdded.mapAttributeField());
addedElement = SalesForecastMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesForecastAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
