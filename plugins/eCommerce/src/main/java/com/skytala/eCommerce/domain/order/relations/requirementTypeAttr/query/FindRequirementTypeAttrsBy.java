package com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.query;
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
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.event.RequirementTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.event.RequirementTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.mapper.RequirementTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.model.RequirementTypeAttr;

public class FindRequirementTypeAttrsBy extends Query {


Map<String, String> filter;
public FindRequirementTypeAttrsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementTypeAttr> foundRequirementTypeAttrs = new ArrayList<RequirementTypeAttr>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("requirementTypeAttrId")) { 
 GenericValue foundElement = delegator.findOne("RequirementTypeAttr", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(RequirementTypeAttr.class); 
 } 
}else { 
 buf = delegator.findAll("RequirementTypeAttr", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundRequirementTypeAttrs.add(RequirementTypeAttrMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new RequirementTypeAttrFound(foundRequirementTypeAttrs);
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
