package com.bookshop.localizationms.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.localizationms.persistence.IEntryRepository;
import com.bookshop.localizationms.persistence.model.Entry;
import com.bookshop.localizationms.service.IEntryService;

@Service
public class EntryService implements IEntryService {

	@Autowired
	private IEntryRepository entryRepository;
	
	@Override
	public List<Entry> findByLang(String lang) {
		return this.entryRepository.findByLang(lang);
	}

	@Override
	public Entry create(Entry entry) {
		entry.setId(UUID.randomUUID().toString());
		return this.entryRepository.save(entry);
	}
}
