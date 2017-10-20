package com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayRespMsg.PaymentGatewayRespMsg;
public class PaymentGatewayRespMsgFound implements Event{

	private List<PaymentGatewayRespMsg> paymentGatewayRespMsgs;

	public PaymentGatewayRespMsgFound(List<PaymentGatewayRespMsg> paymentGatewayRespMsgs) {
		this.paymentGatewayRespMsgs = paymentGatewayRespMsgs;
	}

	public List<PaymentGatewayRespMsg> getPaymentGatewayRespMsgs()	{
		return paymentGatewayRespMsgs;
	}

}
