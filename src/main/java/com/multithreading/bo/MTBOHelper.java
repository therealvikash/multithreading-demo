package com.multithreading.bo;

import org.springframework.stereotype.Component;

import com.multithreading.output.vo.StudentDetailsOutputVO;

@Component
public class MTBOHelper {

	public StudentDetailsOutputVO getStudentDetails(String id) throws Exception {
		if (null == id) {
			throw new Exception("No student ids passed");
		}
		StudentDetailsOutputVO ovo = new StudentDetailsOutputVO();
		ovo.setGrade("8");
		ovo.setId(id);
		ovo.setName(id + " Name not defined");
		ovo.setPercentage("88%");
		ovo.setRollNo("123" + id);
		Thread.sleep(1000);
		return ovo;
	}
}
