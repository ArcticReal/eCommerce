
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.history;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.history.GlAccountHistoryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;


public class FindAllGlAccountHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountHistory> returnVal = new ArrayList<GlAccountHistory>();
try{
List<GenericValue> results = delegator.findAll("GlAccountHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
