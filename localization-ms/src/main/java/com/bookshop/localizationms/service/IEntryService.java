package com.bookshop.localizationms.service;

import java.util.List;

import com.bookshop.localizationms.persistence.model.Entry;

public interface IEntryService {
	
	List<Entry> findByLang(String lang);
	
	Entry create(Entry entry);
	
}
