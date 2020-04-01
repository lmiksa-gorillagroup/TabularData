package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableData {
	protected File file = null;
	protected List<Map<String, String>> data = null;
	protected boolean hasHeaders = true;
	public TableData(String fileName) throws IOException {
		file = new File(fileName);
		this.data = getDataWithHeaders();
	}
	
	protected List<Map<String, String>> getDataWithHeaders() throws IOException {
		List<Map<String, String>> data = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		List<String> headers = getHeaders(br);
		while( (line = br.readLine()) != null) {
			String[] tokens = line.split(",");			
			Map<String, String> row = new HashMap<>(); 
			for(int i = 0; i < tokens.length; i++) {
				row.put(headers.get(i), tokens[i]);
			}
			data.add(row);
		}
		
		return data;
	}
	protected List<String> getHeaders(BufferedReader br) throws IOException {
		List<String> headers = null;
		if(hasHeaders) {
			String line = null;
			if ((line = br.readLine()) != null) {
				headers = Arrays.asList(line.split(","));
			}
		}
		return headers;
	}
	public List<Map<String, String>> getData() {
		return this.data;
	}
	public String getFiled(int rowNumber, String columnName) {
		return data.get(rowNumber).get(columnName);
	}
	public List<Map<String, String>> getDataByColumn(String columnName, String columnValue) {
		List<Map<String, String>> stateData = new ArrayList<>();
		for(Map<String, String> m : this.data) {
			if(m.get(columnName).equals(columnValue)) {
				stateData.add(m);
			}
		}
		return stateData;
	}
}
