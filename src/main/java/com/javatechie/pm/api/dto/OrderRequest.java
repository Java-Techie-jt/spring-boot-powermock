package com.javatechie.pm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

	private int id;
	private String name;
	private int quantity;
	private int price;
	private String emailId;
	private boolean isDiscountable;

}
