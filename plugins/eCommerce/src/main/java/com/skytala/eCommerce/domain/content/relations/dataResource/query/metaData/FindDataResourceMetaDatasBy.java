package com.skytala.eCommerce.domain.content.relations.dataResource.query.metaData;
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
import com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData.DataResourceMetaDataFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.metaData.DataResourceMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData.DataResourceMetaData;

public class FindDataResourceMetaDatasBy extends Query {


Map<String, String> filter;
public FindDataResourceMetaDatasBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceMetaData> foundDataResourceMetaDatas = new ArrayList<DataResourceMetaData>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("dataResourceMetaDataId")) { 
 GenericValue foundElement = delegator.findOne("DataResourceMetaData", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(DataResourceMetaData.class); 
 } 
}else { 
 buf = delegator.findAll("DataResourceMetaData", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundDataResourceMetaDatas.add(DataResourceMetaDataMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new DataResourceMetaDataFound(foundDataResourceMetaDatas);
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
