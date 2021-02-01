package com.bookshop.localizationms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.localizationms.persistence.model.Entry;
import com.bookshop.localizationms.service.IEntryService;

@RestController
@RequestMapping(value="/api/localization")
public class LocalizationController {
	
	@Autowired
	private IEntryService entryService;
	
	@GetMapping(value = "/get/{lang}")
	public ResponseEntity<List<Entry>> getByCountry(@PathVariable("lang") String lang) {
		return ResponseEntity.ok(entryService.findByLang(lang));
	}
	
	@PutMapping(value = "/create")
	public ResponseEntity<Entry> create(@RequestBody Entry entry) {
		return ResponseEntity.ok(entryService.create(entry));
	}
	
}
