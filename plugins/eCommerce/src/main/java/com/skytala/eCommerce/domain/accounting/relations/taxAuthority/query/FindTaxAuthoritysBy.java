package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.query;
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
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityAdded;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.event.TaxAuthorityFound;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.TaxAuthorityMapper;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.TaxAuthority;

public class FindTaxAuthoritysBy extends Query {


Map<String, String> filter;
public FindTaxAuthoritysBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TaxAuthority> foundTaxAuthoritys = new ArrayList<TaxAuthority>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("taxAuthorityId")) { 
 GenericValue foundElement = delegator.findOne("TaxAuthority", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(TaxAuthority.class); 
 } 
}else { 
 buf = delegator.findAll("TaxAuthority", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundTaxAuthoritys.add(TaxAuthorityMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new TaxAuthorityFound(foundTaxAuthoritys);
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
