
package com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.event.PaymentTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.mapper.PaymentTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.model.PaymentTypeAttr;


public class FindAllPaymentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentTypeAttr> returnVal = new ArrayList<PaymentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("PaymentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
