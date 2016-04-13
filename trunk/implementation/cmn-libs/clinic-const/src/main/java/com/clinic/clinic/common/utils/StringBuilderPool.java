package com.clinic.clinic.common.utils;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class StringBuilderPool extends GenericObjectPool<StringBuilder> {
	
	public static final StringBuilderPool POOL = new StringBuilderPool();
	
	private static class PooledStringBuilderFactory
		extends BasePooledObjectFactory<StringBuilder> {

		@Override
		public StringBuilder create() throws Exception {
			return new StringBuilder();
		}

		@Override
		public PooledObject<StringBuilder> wrap(StringBuilder sb) {
			return new DefaultPooledObject<StringBuilder>(sb);
		}
	}
	
	public StringBuilderPool() {
		super(new PooledStringBuilderFactory());
	}
}
