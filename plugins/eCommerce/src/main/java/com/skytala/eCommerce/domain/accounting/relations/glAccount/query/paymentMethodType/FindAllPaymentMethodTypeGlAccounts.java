
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.paymentMethodType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentMethodType.PaymentMethodTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentMethodType.PaymentMethodTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType.PaymentMethodTypeGlAccount;


public class FindAllPaymentMethodTypeGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentMethodTypeGlAccount> returnVal = new ArrayList<PaymentMethodTypeGlAccount>();
try{
List<GenericValue> results = delegator.findAll("PaymentMethodTypeGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentMethodTypeGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentMethodTypeGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
