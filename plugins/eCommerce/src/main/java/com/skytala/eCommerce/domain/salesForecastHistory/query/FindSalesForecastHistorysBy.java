package com.skytala.eCommerce.domain.salesForecastHistory.query;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.salesForecastHistory.event.SalesForecastHistoryAdded;
import com.skytala.eCommerce.domain.salesForecastHistory.event.SalesForecastHistoryFound;
import com.skytala.eCommerce.domain.salesForecastHistory.mapper.SalesForecastHistoryMapper;
import com.skytala.eCommerce.domain.salesForecastHistory.model.SalesForecastHistory;

public class FindSalesForecastHistorysBy extends Query {


Map<String, String> filter;
public FindSalesForecastHistorysBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesForecastHistory> foundSalesForecastHistorys = new ArrayList<SalesForecastHistory>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("salesForecastHistoryId")) { 
 GenericValue foundElement = delegator.findOne("SalesForecastHistory", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(SalesForecastHistory.class); 
 } 
}else { 
 buf = delegator.findAll("SalesForecastHistory", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundSalesForecastHistorys.add(SalesForecastHistoryMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new SalesForecastHistoryFound(foundSalesForecastHistorys);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
public void setFilter(Map<String, String> newFilter) {
this.filter = newFilter;
}
}
