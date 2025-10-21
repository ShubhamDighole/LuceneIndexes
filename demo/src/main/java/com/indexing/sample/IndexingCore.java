package com.indexing.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexingCore {
	
	@GetMapping("/startIndex")
	public static void main(String[] args) {
		System.out.println("Lucene Indexing");
	}

}
