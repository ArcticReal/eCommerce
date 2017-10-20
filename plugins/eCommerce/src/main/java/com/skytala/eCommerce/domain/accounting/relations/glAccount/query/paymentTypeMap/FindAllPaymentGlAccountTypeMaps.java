
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.paymentTypeMap;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap.PaymentGlAccountTypeMapFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentTypeMap.PaymentGlAccountTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;


public class FindAllPaymentGlAccountTypeMaps extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGlAccountTypeMap> returnVal = new ArrayList<PaymentGlAccountTypeMap>();
try{
List<GenericValue> results = delegator.findAll("PaymentGlAccountTypeMap", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGlAccountTypeMapMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGlAccountTypeMapFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
