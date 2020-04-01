package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.TableData;

class TestStateTableData {
	private String path = "/home/lavi/git/covid-19-data/";
	private String fileName = "us-states.csv";
	@Test
	void testSingleState() throws IOException {
		TableData td = new TableData(path+fileName);
		assertTrue("Washington".equalsIgnoreCase(td.getFiled(1, "state")));
	}
	@Test
	void testDataForSpecificState() throws IOException {
		TableData td = new TableData(path+fileName);
		assertTrue(td.getDataByColumn("state", "Ohio").size() > 1);		
	}
}