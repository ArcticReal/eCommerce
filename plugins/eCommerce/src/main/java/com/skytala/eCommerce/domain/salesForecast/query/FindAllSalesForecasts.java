
package com.skytala.eCommerce.domain.salesForecast.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.salesForecast.event.SalesForecastFound;
import com.skytala.eCommerce.domain.salesForecast.mapper.SalesForecastMapper;
import com.skytala.eCommerce.domain.salesForecast.model.SalesForecast;


public class FindAllSalesForecasts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesForecast> returnVal = new ArrayList<SalesForecast>();
try{
List<GenericValue> results = delegator.findAll("SalesForecast", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesForecastMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesForecastFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
