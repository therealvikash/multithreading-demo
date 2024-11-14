package com.multithreading.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multithreading.bo.MTBO;
import com.multithreading.input.vo.StudentDetailsInputVO;
import com.multithreading.output.vo.StudentDetailsOutputVO;

@Component
public class MTService {

	@Autowired
	private MTBO mtBO;

	public List<StudentDetailsOutputVO> getStudentDetails(StudentDetailsInputVO ivo) throws Exception {
		return mtBO.getStudentDetails(ivo);
	}

}
