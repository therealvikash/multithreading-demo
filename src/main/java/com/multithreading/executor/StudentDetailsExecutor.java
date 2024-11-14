package com.multithreading.executor;

import java.util.concurrent.Callable;

import com.multithreading.bo.MTBOHelper;
import com.multithreading.output.vo.StudentDetailsOutputVO;

public class StudentDetailsExecutor implements Callable<StudentDetailsOutputVO> {

	private MTBOHelper helper;
	private String id;

	public StudentDetailsExecutor(MTBOHelper helper, String id) {
		this.helper = helper;
		this.id = id;
	}
	
	@Override
	public StudentDetailsOutputVO call() throws Exception {
		return helper.getStudentDetails(id);
	}
}
