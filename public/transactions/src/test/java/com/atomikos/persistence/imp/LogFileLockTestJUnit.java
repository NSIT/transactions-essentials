package com.atomikos.persistence.imp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atomikos.persistence.LogException;

public class LogFileLockTestJUnit {

	private LogFileLock lock;
	
	@Before
	public void setUp() throws Exception {
		lock = new LogFileLock("./", "LogFileLockTest");
	}
	
	@After
	public void tearDown() throws Exception {
		if (lock != null) lock.releaseLock();
	}

	@Test
	public void testLockWorksForFirstAcquisition() throws LogException {
		lock.acquireLock();
	}
	
	@Test(expected=LogException.class)
	public void testLockFailsForSecondAcquisition() throws LogException {
		lock.acquireLock();
		lock.acquireLock();
	}

	@Test
	public void testLockWorksAfterAcquisitionAndRelease() throws LogException {
		lock.acquireLock();
		lock.releaseLock();
		lock.acquireLock();
	}
	
}
