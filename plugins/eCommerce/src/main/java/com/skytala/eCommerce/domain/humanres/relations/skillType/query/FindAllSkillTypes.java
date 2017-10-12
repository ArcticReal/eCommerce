
package com.skytala.eCommerce.domain.humanres.relations.skillType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.skillType.event.SkillTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.skillType.mapper.SkillTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.skillType.model.SkillType;


public class FindAllSkillTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SkillType> returnVal = new ArrayList<SkillType>();
try{
List<GenericValue> results = delegator.findAll("SkillType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SkillTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SkillTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
