
package com.skytala.eCommerce.domain.party.relations.termType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.termType.event.TermTypeFound;
import com.skytala.eCommerce.domain.party.relations.termType.mapper.TermTypeMapper;
import com.skytala.eCommerce.domain.party.relations.termType.model.TermType;


public class FindAllTermTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TermType> returnVal = new ArrayList<TermType>();
try{
List<GenericValue> results = delegator.findAll("TermType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TermTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TermTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
