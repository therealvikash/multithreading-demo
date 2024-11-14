package com.multithreading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multithreading.input.vo.StudentDetailsInputVO;
import com.multithreading.output.vo.StudentDetailsOutputVO;
import com.multithreading.service.MTService;

@RestController
@RequestMapping("/test")
public class MTController {

	@Autowired
	private MTService mtService;

	@PostMapping("/multithreading")
	public List<StudentDetailsOutputVO> getStudentDetails(@RequestBody StudentDetailsInputVO ivo) throws Exception {
		int s = 0;
		return mtService.getStudentDetails(ivo);
	}
}
