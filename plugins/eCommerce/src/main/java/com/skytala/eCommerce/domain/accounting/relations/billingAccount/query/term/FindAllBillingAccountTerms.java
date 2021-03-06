
package com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.term;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term.BillingAccountTermFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.term.BillingAccountTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.term.BillingAccountTerm;


public class FindAllBillingAccountTerms extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BillingAccountTerm> returnVal = new ArrayList<BillingAccountTerm>();
try{
List<GenericValue> results = delegator.findAll("BillingAccountTerm", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BillingAccountTermMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BillingAccountTermFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
