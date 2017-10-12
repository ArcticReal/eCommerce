
package com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.event.AcctgTransAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.mapper.AcctgTransAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.model.AcctgTransAttribute;


public class FindAllAcctgTransAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AcctgTransAttribute> returnVal = new ArrayList<AcctgTransAttribute>();
try{
List<GenericValue> results = delegator.findAll("AcctgTransAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AcctgTransAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AcctgTransAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
