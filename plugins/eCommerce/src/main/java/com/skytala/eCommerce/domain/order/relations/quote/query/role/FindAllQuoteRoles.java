
package com.skytala.eCommerce.domain.order.relations.quote.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.role.QuoteRoleFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.role.QuoteRoleMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.role.QuoteRole;


public class FindAllQuoteRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteRole> returnVal = new ArrayList<QuoteRole>();
try{
List<GenericValue> results = delegator.findAll("QuoteRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
