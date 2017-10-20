
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transAttribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transAttribute.FinAccountTransAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transAttribute.FinAccountTransAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;


public class FindAllFinAccountTransAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTransAttribute> returnVal = new ArrayList<FinAccountTransAttribute>();
try{
List<GenericValue> results = delegator.findAll("FinAccountTransAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTransAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTransAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
