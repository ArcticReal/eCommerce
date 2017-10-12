package com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.event.SurveyResponseAnswerAdded;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.mapper.SurveyResponseAnswerMapper;
import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.model.SurveyResponseAnswer;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSurveyResponseAnswer extends Command {

private SurveyResponseAnswer elementToBeAdded;
public AddSurveyResponseAnswer(SurveyResponseAnswer elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SurveyResponseAnswer addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSurveyMultiRespColId(delegator.getNextSeqId("SurveyResponseAnswer"));
GenericValue newValue = delegator.makeValue("SurveyResponseAnswer", elementToBeAdded.mapAttributeField());
addedElement = SurveyResponseAnswerMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SurveyResponseAnswerAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
