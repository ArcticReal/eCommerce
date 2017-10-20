
package com.skytala.eCommerce.domain.content.relations.dataResource.query.audio;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.audio.AudioDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.audio.AudioDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.audio.AudioDataResource;


public class FindAllAudioDataResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AudioDataResource> returnVal = new ArrayList<AudioDataResource>();
try{
List<GenericValue> results = delegator.findAll("AudioDataResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AudioDataResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AudioDataResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
