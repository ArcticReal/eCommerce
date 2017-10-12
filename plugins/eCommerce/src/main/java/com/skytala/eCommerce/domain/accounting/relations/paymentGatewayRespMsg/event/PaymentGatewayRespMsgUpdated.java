package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.model.PaymentGatewayRespMsg;
public class PaymentGatewayRespMsgUpdated implements Event{

	private boolean success;

	public PaymentGatewayRespMsgUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
