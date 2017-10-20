
package com.skytala.eCommerce.domain.marketing.relations.salesForecast.query.detail;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.event.detail.SalesForecastDetailFound;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.mapper.detail.SalesForecastDetailMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesForecast.model.detail.SalesForecastDetail;


public class FindAllSalesForecastDetails extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesForecastDetail> returnVal = new ArrayList<SalesForecastDetail>();
try{
List<GenericValue> results = delegator.findAll("SalesForecastDetail", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesForecastDetailMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesForecastDetailFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
