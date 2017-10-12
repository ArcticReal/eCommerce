package com.skytala.eCommerce.domain.content.relations.surveyResponse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyResponse.event.SurveyResponseAdded;
import com.skytala.eCommerce.domain.content.relations.surveyResponse.mapper.SurveyResponseMapper;
import com.skytala.eCommerce.domain.content.relations.surveyResponse.model.SurveyResponse;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyResponse extends Command {

private SurveyResponse elementToBeAdded;
public AddSurveyResponse(SurveyResponse elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyResponse addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyResponseId(delegator.getNextSeqId("SurveyResponse"));
GenericValue newValue = delegator.makeValue("SurveyResponse", elementToBeAdded.mapAttributeField());
addedElement = SurveyResponseMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyResponseAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
