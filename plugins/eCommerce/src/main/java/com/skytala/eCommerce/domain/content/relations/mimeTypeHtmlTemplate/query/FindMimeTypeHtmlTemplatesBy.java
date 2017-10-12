package com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.query;
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
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.event.MimeTypeHtmlTemplateAdded;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.event.MimeTypeHtmlTemplateFound;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.mapper.MimeTypeHtmlTemplateMapper;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.model.MimeTypeHtmlTemplate;

public class FindMimeTypeHtmlTemplatesBy extends Query {


Map<String, String> filter;
public FindMimeTypeHtmlTemplatesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MimeTypeHtmlTemplate> foundMimeTypeHtmlTemplates = new ArrayList<MimeTypeHtmlTemplate>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("mimeTypeHtmlTemplateId")) { 
 GenericValue foundElement = delegator.findOne("MimeTypeHtmlTemplate", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(MimeTypeHtmlTemplate.class); 
 } 
}else { 
 buf = delegator.findAll("MimeTypeHtmlTemplate", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundMimeTypeHtmlTemplates.add(MimeTypeHtmlTemplateMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new MimeTypeHtmlTemplateFound(foundMimeTypeHtmlTemplates);
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
