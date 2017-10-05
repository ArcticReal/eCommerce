package com.skytala.eCommerce.domain.surveyMultiResp.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.surveyMultiResp.event.SurveyMultiRespAdded;
import com.skytala.eCommerce.domain.surveyMultiResp.mapper.SurveyMultiRespMapper;
import com.skytala.eCommerce.domain.surveyMultiResp.model.SurveyMultiResp;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyMultiResp extends Command {

private SurveyMultiResp elementToBeAdded;
public AddSurveyMultiResp(SurveyMultiResp elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyMultiResp addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyMultiRespId(delegator.getNextSeqId("SurveyMultiResp"));
GenericValue newValue = delegator.makeValue("SurveyMultiResp", elementToBeAdded.mapAttributeField());
addedElement = SurveyMultiRespMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyMultiRespAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
