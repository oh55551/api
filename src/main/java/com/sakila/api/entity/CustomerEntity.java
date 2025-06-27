package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer")
@DynamicInsert
@Data
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coustomer_id")
	private int coustomerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "last_update")
	private Timestamp lastUpdate;
	
   	@Column(name = "store_id") 
	private int storeId; 
    /*
    @ManyToOne 
	@JoinColumn(name = "store_id") // 이후에 단방향(customer에서 store)설정으로 수정이 필요
	private StoreEntity storeEntity;
    */

	@ManyToOne 
	@JoinColumn(name = "address_id") // 단방향(customer에서 address)
	private AddressEntity addressEntity;
}