package edu.csupomona.cs585.aspectj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.After;

@Aspect
public class MyTracer {
	
	List<Data> resultList = new ArrayList<Data>();
	
	private Data temp;
	
	@Pointcut("execution(public static void *.main(String[]))")
	public void mains() {}
	
	@Pointcut("withincode(* edu.csupomona.cs585.aspectj.*.*(..)) " +
			"&& !withincode(* edu.csupomona.cs585.aspectj.MyTracer.*(..)) " +
			"&& !withincode(* edu.csupomona.cs585.aspectj.Data.*(..)) ")
	public void statement() {}
	
	@Before("statement()")
	public void totalTimes(JoinPoint jp)
	{
		int lineNum = jp.getSourceLocation().getLine();
		boolean isExist = false;
		if(resultList.isEmpty()){
			temp = new Data();
			temp.setClassName("***" + jp.getSourceLocation().getFileName() + "***");
			resultList.add(temp);
			temp = new Data();
			temp.setLineNum(lineNum);
			temp.setTotalCount(1);
			resultList.add(temp);
		}else{
			for(Data data : resultList){
				if(data.getLineNum() == lineNum){
					data.setTotalCount(data.getTotalCount() + 1);
					isExist = true;
					break;
				}
			}
			if(isExist == false){
				temp = new Data();
				temp.setLineNum(lineNum);
				temp.setTotalCount(1);
				resultList.add(temp);
			}
		}
	}
	
	@AfterReturning("statement()")
	public void countNormalExecution(JoinPoint jp){
		int lineNum = jp.getSourceLocation().getLine();
		for(Data data : resultList){
				if(data.getLineNum() == lineNum){
					data.setNormalCount(data.getNormalCount() + 1);
				}
		}
	}
	
	@AfterThrowing("statement()")
	public void countExceptionalExecution(JoinPoint jp){
		int lineNum = jp.getSourceLocation().getLine();
		for(Data data : resultList){
				if(data.getLineNum() == lineNum){
					data.setExceptionalCount(data.getExceptionalCount() + 1);
				}
		}
	}
	
	@After("mains()")
	public void printResult(){
		Collections.sort(resultList);    //sort List
		for(Data data : resultList){
			if(data.getClassName() != null){
				System.out.println(data.getClassName());
			}else{
				System.out.println(data.getLineNum() + ":\t" + data.getTotalCount() + "  " + data.getNormalCount() + "  " + data.getExceptionalCount());
			}
			
		}
	}
}
