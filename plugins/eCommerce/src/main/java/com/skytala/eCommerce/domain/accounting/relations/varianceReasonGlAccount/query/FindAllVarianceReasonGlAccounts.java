
package com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.event.VarianceReasonGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.mapper.VarianceReasonGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.model.VarianceReasonGlAccount;


public class FindAllVarianceReasonGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<VarianceReasonGlAccount> returnVal = new ArrayList<VarianceReasonGlAccount>();
try{
List<GenericValue> results = delegator.findAll("VarianceReasonGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(VarianceReasonGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new VarianceReasonGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
