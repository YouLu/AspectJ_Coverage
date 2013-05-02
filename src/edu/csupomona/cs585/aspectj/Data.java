package edu.csupomona.cs585.aspectj;

public class Data implements Comparable<Data> {

	private int lineNum = 0;;
	private int totalCount = 0;
	private int normalCount = 0;
	private int exceptionalCount = 0;
	private String className = null;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNormalCount() {
		return normalCount;
	}
	public void setNormalCount(int normalCount) {
		this.normalCount = normalCount;
	}
	public int getExceptionalCount() {
		return exceptionalCount;
	}
	public void setExceptionalCount(int exceptionalCount) {
		this.exceptionalCount = exceptionalCount;
	}

	@Override
	public int compareTo(Data o) {
		return this.getLineNum() <= o.getLineNum() ? 0 : 1;
	}
}
