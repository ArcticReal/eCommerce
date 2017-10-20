package com.skytala.eCommerce.domain.marketing.relations.salesForecast.command.history;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history.SalesForecastHistoryAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.history.SalesForecastHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.history.SalesForecastHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesForecastHistory extends Command {

private SalesForecastHistory elementToBeAdded;
public AddSalesForecastHistory(SalesForecastHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesForecastHistory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSalesForecastHistoryId(delegator.getNextSeqId("SalesForecastHistory"));
GenericValue newValue = delegator.makeValue("SalesForecastHistory", elementToBeAdded.mapAttributeField());
addedElement = SalesForecastHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesForecastHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
