package com.my;
import java.io.*;

public class Record
{
	String fileAbsPath;
    int id;

	public Record(String fileAbsPath)
	{
		this.fileAbsPath = fileAbsPath;
	}



	public void setFileAbsPath(String fileAbsPath)
	{
		this.fileAbsPath = fileAbsPath;
	}

	public String getFileAbsPath()
	{
		return fileAbsPath;
	}}
