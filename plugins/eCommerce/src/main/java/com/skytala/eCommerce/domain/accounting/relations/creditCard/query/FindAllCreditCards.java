
package com.skytala.eCommerce.domain.accounting.relations.creditCard.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.event.CreditCardFound;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.mapper.CreditCardMapper;
import com.skytala.eCommerce.domain.accounting.relations.creditCard.model.CreditCard;


public class FindAllCreditCards extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CreditCard> returnVal = new ArrayList<CreditCard>();
try{
List<GenericValue> results = delegator.findAll("CreditCard", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CreditCardMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CreditCardFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
