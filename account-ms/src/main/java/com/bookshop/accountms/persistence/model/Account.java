package com.bookshop.accountms.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "accounts")
@Builder
@Getter
@Setter
public class Account {
	@Id
	@Field(name = "id")
	private String id;
	
	@Field(name = "name")
	private String name;
}
