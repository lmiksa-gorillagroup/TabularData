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

public class TableData <T> {
	protected File file = null;
	protected List<T> data = null;
	protected boolean hasHeaders = true;
	public TableData(String fileName) throws IOException {
		file = new File(fileName);
		if(hasHeaders) {
			this.data = getDataWithHeaders();
		} else {
			this.data = getDataWithOutHeaders();
		}
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	protected List<T> getDataWithOutHeaders() throws IOException {
		List<T> data = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while( (line = br.readLine()) != null) {
			String[] tokens = line.split(",");			
			T row = (T) new ArrayList<>(); 
			for(int i = 0; i < tokens.length; i++) {
				((List<String>)row).add(tokens[i]);
			}
			data.add(row);
		}
		
		return data;
	}
	
	@SuppressWarnings({ "unchecked" })
	protected List<T> getDataWithHeaders() throws IOException {
		List<T> data = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		List<String> headers = getHeaders(br);
		while( (line = br.readLine()) != null) {
			String[] tokens = line.split(",");			
			T row = (T) new HashMap<>(); 
			for(int i = 0; i < tokens.length; i++) {
				((Map<String, String>)row).put(headers.get(i), tokens[i]);
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
	public List<T> getData() {
		return this.data;
	}
	
	@SuppressWarnings({ "unchecked" })
	public String getFiled(int rowNumber, String columnName) {
		return ((Map<String, String>)data.get(rowNumber)).get(columnName);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getDataByColumnName(String columnName, String columnValue) {
		List<T> stateData = new ArrayList<>();
		for(T m : this.data) {
			if( ((Map<String, String>)m).get(columnName).equals(columnValue) ) {
				stateData.add(m);
			}
		}
		return stateData;
	}
}
