package com.sakila.api.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AddressDto {
	private int addressId;
	private String address;
	private String address2;
	private String district;
	private String postCode;
	private String phone;
	private String location;
	private Timestamp lastUpdate;
	private int cityId;
}
