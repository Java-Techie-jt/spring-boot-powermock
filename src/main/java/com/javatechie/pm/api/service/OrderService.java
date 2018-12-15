package com.javatechie.pm.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.javatechie.pm.api.dto.OrderRequest;
import com.javatechie.pm.api.dto.OrderResponse;
import com.javatechie.pm.api.util.NotificationUtil;

@Service
public class OrderService {

	public OrderResponse checkoutOrder(OrderRequest order) {
		// call DAO
		int discount=addDiscount(order);
		order.setPrice(order.getPrice()-discount);
		String message = NotificationUtil.sendEmail(order.getEmailId());
		return new OrderResponse(order, message, HttpStatus.OK.value());
	}

	private int addDiscount(OrderRequest order) {
		System.out.println("called...");
		int price = order.getPrice();
		int discountAMount = 0;
		if (order.isDiscountable()) {
			if (order.getPrice() > 1000) {
				discountAMount = price * 10 / 100;
			} else {
				discountAMount = price;
			}
		} else {
			discountAMount = price;
		}
		return discountAMount;
	}
}
