package com.javatechie.pm.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.javatechie.pm.api.dto.OrderRequest;
import com.javatechie.pm.api.dto.OrderResponse;
import com.javatechie.pm.api.service.OrderService;
import com.javatechie.pm.api.util.NotificationUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.javatechie.pm.api.*")
public class PowermockApiApplicationTests {

	@InjectMocks
	private OrderService service;

	OrderRequest request = new OrderRequest(111, "Mobile", 1, 10000, "test@gmail.com", true);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(NotificationUtil.class);
	}

	@Test
	public void testStaticMethod() {
		// Given
		String emailid = "test@gmail.com";
		PowerMockito.mockStatic(NotificationUtil.class);
		// When
		when(NotificationUtil.sendEmail(emailid)).thenReturn("success");
		// Then
		OrderResponse response = service.checkoutOrder(request);
		assertEquals("success", response.getMessage());
	}

	@Test
	public void testPrivateMethod() throws Exception {
		// When
		OrderService spy = PowerMockito.spy(service);
		doReturn(2000).when(spy, "addDiscount", ArgumentMatchers.any());
		// Then
		OrderResponse response = spy.checkoutOrder(request);
		int price = response.getResponse().getPrice();// 9000
		System.out.println("price : " + price);
		assertEquals(8000, price);
	}

}
