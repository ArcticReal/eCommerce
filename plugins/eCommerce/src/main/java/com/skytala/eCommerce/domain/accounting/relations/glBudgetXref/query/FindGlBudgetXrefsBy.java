package com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.query;
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
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.event.GlBudgetXrefAdded;
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.event.GlBudgetXrefFound;
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.mapper.GlBudgetXrefMapper;
import com.skytala.eCommerce.domain.accounting.relations.glBudgetXref.model.GlBudgetXref;

public class FindGlBudgetXrefsBy extends Query {


Map<String, String> filter;
public FindGlBudgetXrefsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlBudgetXref> foundGlBudgetXrefs = new ArrayList<GlBudgetXref>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("glBudgetXrefId")) { 
 GenericValue foundElement = delegator.findOne("GlBudgetXref", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(GlBudgetXref.class); 
 } 
}else { 
 buf = delegator.findAll("GlBudgetXref", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundGlBudgetXrefs.add(GlBudgetXrefMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new GlBudgetXrefFound(foundGlBudgetXrefs);
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
