package com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.event.SalesForecastDetailAdded;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.mapper.SalesForecastDetailMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecastDetail.model.SalesForecastDetail;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalesForecastDetail extends Command {

private SalesForecastDetail elementToBeAdded;
public AddSalesForecastDetail(SalesForecastDetail elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalesForecastDetail addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSalesForecastDetailId(delegator.getNextSeqId("SalesForecastDetail"));
GenericValue newValue = delegator.makeValue("SalesForecastDetail", elementToBeAdded.mapAttributeField());
addedElement = SalesForecastDetailMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalesForecastDetailAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
