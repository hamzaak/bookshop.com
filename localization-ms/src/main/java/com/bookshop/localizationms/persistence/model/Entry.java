package com.bookshop.localizationms.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "localization")
@Builder
@Getter
@Setter
public class Entry {
	@Id
	@Field(name = "id")
	private String id;
	
	@Field(name = "key")
	private String key;
	
	@Field(name = "lang")
	private String lang;
	
	@Field(name = "value")
	private String value;

}
