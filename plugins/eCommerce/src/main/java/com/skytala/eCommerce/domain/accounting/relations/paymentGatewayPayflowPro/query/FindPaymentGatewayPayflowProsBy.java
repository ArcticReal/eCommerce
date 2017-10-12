package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.query;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event.PaymentGatewayPayflowProAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.event.PaymentGatewayPayflowProFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.mapper.PaymentGatewayPayflowProMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayflowPro.model.PaymentGatewayPayflowPro;

public class FindPaymentGatewayPayflowProsBy extends Query {


Map<String, String> filter;
public FindPaymentGatewayPayflowProsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayPayflowPro> foundPaymentGatewayPayflowPros = new ArrayList<PaymentGatewayPayflowPro>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("paymentGatewayPayflowProId")) { 
 GenericValue foundElement = delegator.findOne("PaymentGatewayPayflowPro", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(PaymentGatewayPayflowPro.class); 
 } 
}else { 
 buf = delegator.findAll("PaymentGatewayPayflowPro", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundPaymentGatewayPayflowPros.add(PaymentGatewayPayflowProMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new PaymentGatewayPayflowProFound(foundPaymentGatewayPayflowPros);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
public void setFilter(Map<String, String> newFilter) {
this.filter = newFilter;
}
}
