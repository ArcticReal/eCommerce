
package com.skytala.eCommerce.domain.humanres.relations.payHistory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryFound;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.mapper.PayHistoryMapper;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;


public class FindAllPayHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PayHistory> returnVal = new ArrayList<PayHistory>();
try{
List<GenericValue> results = delegator.findAll("PayHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PayHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PayHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
