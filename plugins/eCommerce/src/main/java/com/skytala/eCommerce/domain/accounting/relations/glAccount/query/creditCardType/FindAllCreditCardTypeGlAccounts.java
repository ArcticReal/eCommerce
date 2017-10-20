
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.creditCardType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.creditCardType.CreditCardTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.creditCardType.CreditCardTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.creditCardType.CreditCardTypeGlAccount;


public class FindAllCreditCardTypeGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CreditCardTypeGlAccount> returnVal = new ArrayList<CreditCardTypeGlAccount>();
try{
List<GenericValue> results = delegator.findAll("CreditCardTypeGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CreditCardTypeGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CreditCardTypeGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
