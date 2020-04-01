package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.TableData;

public class TestStateTableData {
//	private String path = "/home/lavi/git/covid-19-data/";
	private String path = "/Users/lavi/git/covid-19-data/";
	private String fileName = "us-states.csv";
	@org.junit.Test
	public void testSingleState() throws IOException {
		TableData td = new TableData(path+fileName);
		assertTrue("Washington".equalsIgnoreCase(td.getFiled(1, "state")));
	}
	@org.junit.Test
	public void testDataForSpecificState() throws IOException {
		TableData td = new TableData(path+fileName);
		assertTrue(td.getDataByColumn("state", "Ohio").size() > 1);		
	}
}
