package com.skytala.eCommerce.domain.content.relations.survey.query.response;
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
import com.skytala.eCommerce.domain.content.relations.survey.event.response.SurveyResponseFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.response.SurveyResponseMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.response.SurveyResponse;

public class FindSurveyResponsesBy extends Query {


Map<String, String> filter;
public FindSurveyResponsesBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public Event execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyResponse> foundSurveyResponses = new ArrayList<SurveyResponse>();

try{
List<GenericValue> buf = new LinkedList<>();
if(filter.size()==1&&filter.containsKey("surveyResponseId")) { 
 GenericValue foundElement = delegator.findOne("SurveyResponse", false, filter);
if(foundElement != null) { 
buf.add(foundElement);
}else { 
throw new RecordNotFoundException(SurveyResponse.class); 
 } 
}else { 
 buf = delegator.findAll("SurveyResponse", false); 
 }

for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundSurveyResponses.add(SurveyResponseMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Event resultingEvent = new SurveyResponseFound(foundSurveyResponses);
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
