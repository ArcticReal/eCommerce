
package com.skytala.eCommerce.domain.marketing.relations.salesForecast.query.history;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.history.SalesForecastHistoryFound;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.history.SalesForecastHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.history.SalesForecastHistory;


public class FindAllSalesForecastHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesForecastHistory> returnVal = new ArrayList<SalesForecastHistory>();
try{
List<GenericValue> results = delegator.findAll("SalesForecastHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesForecastHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesForecastHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
