package com.sakila.api.entity;

public interface CustomerMapping {
	int getCustomerId();
	String getFirstName();
	String getLastName();
}

// 4개의 엔티티 전체 리스트 -> Mapping타입의 페이지리스트타입으로 변경
