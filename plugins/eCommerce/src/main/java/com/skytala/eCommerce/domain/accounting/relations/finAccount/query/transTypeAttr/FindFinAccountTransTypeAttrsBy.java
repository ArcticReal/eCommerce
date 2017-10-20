package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transTypeAttr;
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
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transTypeAttr.FinAccountTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;

public class FindFinAccountTransTypeAttrsBy extends Query {


Map<String, String> filter;
public FindFinAccountTransTypeAttrsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTransTypeAttr> foundFinAccountTransTypeAttrs = new ArrayList<FinAccountTransTypeAttr>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("finAccountTransTypeAttrId")) { 
 GenericValue foundElement = delegator.findOne("FinAccountTransTypeAttr", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(FinAccountTransTypeAttr.class); 
 } 
}else { 
 buf = delegator.findAll("FinAccountTransTypeAttr", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundFinAccountTransTypeAttrs.add(FinAccountTransTypeAttrMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new FinAccountTransTypeAttrFound(foundFinAccountTransTypeAttrs);
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
