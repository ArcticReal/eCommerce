
package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyFound;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.mapper.ValueLinkKeyMapper;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;


public class FindAllValueLinkKeys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ValueLinkKey> returnVal = new ArrayList<ValueLinkKey>();
try{
List<GenericValue> results = delegator.findAll("ValueLinkKey", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ValueLinkKeyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ValueLinkKeyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
