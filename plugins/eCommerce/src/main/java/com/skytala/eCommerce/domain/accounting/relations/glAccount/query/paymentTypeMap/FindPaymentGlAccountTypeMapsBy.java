package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.paymentTypeMap;
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
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.paymentTypeMap.PaymentGlAccountTypeMapFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentTypeMap.PaymentGlAccountTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap.PaymentGlAccountTypeMap;

public class FindPaymentGlAccountTypeMapsBy extends Query {


Map<String, String> filter;
public FindPaymentGlAccountTypeMapsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGlAccountTypeMap> foundPaymentGlAccountTypeMaps = new ArrayList<PaymentGlAccountTypeMap>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("paymentGlAccountTypeMapId")) { 
 GenericValue foundElement = delegator.findOne("PaymentGlAccountTypeMap", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(PaymentGlAccountTypeMap.class); 
 } 
}else { 
 buf = delegator.findAll("PaymentGlAccountTypeMap", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundPaymentGlAccountTypeMaps.add(PaymentGlAccountTypeMapMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new PaymentGlAccountTypeMapFound(foundPaymentGlAccountTypeMaps);
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
