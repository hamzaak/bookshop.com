package com.bookshop.localizationms.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookshop.localizationms.persistence.model.Entry;

@Repository
public interface IEntryRepository extends MongoRepository<Entry, String> {
	List<Entry> findByLang(String lang);
}
