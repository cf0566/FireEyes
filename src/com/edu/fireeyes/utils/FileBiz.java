package com.edu.fireeyes.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class FileBiz {
	
	public static boolean writeToFile(Context context,Map<String, Object> data){
		
		String url = (String) data.get("url");
		
		BufferedWriter bw = null;
		
		try {
			FileOutputStream fos = context.openFileOutput("url.txt", Context.MODE_PRIVATE);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(url);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	/**
	 * 从文件中读取数据
	 */
	public static Map<String, Object> readFromFile(Context context) {
		
		BufferedReader br = null;
		
		try {
			FileInputStream fis = context.openFileInput("url.txt");
			br = new BufferedReader(new InputStreamReader(fis));
			
			String url = br.readLine();
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("url", url);
			
			return data;
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
		
	}
	
}
