package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.query;
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
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceAdded;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.event.PayrollPreferenceFound;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.mapper.PayrollPreferenceMapper;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model.PayrollPreference;

public class FindPayrollPreferencesBy extends Query {


Map<String, String> filter;
public FindPayrollPreferencesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PayrollPreference> foundPayrollPreferences = new ArrayList<PayrollPreference>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("payrollPreferenceId")) { 
 GenericValue foundElement = delegator.findOne("PayrollPreference", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(PayrollPreference.class); 
 } 
}else { 
 buf = delegator.findAll("PayrollPreference", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundPayrollPreferences.add(PayrollPreferenceMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new PayrollPreferenceFound(foundPayrollPreferences);
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
