package com.java.batch.stringbatch.configuration;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListener {
	
	@BeforeChunk
	public void beforeChunk(ChunkContext conkContext) {
		System.out.println("before the chunk-->>");
	}

	
	@AfterChunk
	public void afterChunk(ChunkContext conkContext) {
		System.out.println("after the chunk-->>");
	}
}
