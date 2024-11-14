package com.multithreading.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multithreading.executor.StudentDetailsExecutor;
import com.multithreading.input.vo.StudentDetailsInputVO;
import com.multithreading.output.vo.StudentDetailsOutputVO;

import lombok.extern.java.Log;

@Component
@Log
public class MTBO {
	
	@Autowired
	private MTBOHelper helper;

	public List<StudentDetailsOutputVO> getStudentDetails(StudentDetailsInputVO ivo) throws Exception {
		List<StudentDetailsOutputVO> ovos = new ArrayList<>();
		
		Collection<Callable<StudentDetailsOutputVO>> studentDetails = createProcessors(ivo);
		int numsOfThread = studentDetails.size();
		ExecutorService studenDetailsExecutor  = Executors.newFixedThreadPool(numsOfThread);
		try {
			List<Future<StudentDetailsOutputVO>> results = studenDetailsExecutor.invokeAll(studentDetails);
			for (Future<StudentDetailsOutputVO> result : results) {
				StudentDetailsOutputVO response = result.get();
				ovos.add(response);
			}
		} catch (InterruptedException e) {
			studenDetailsExecutor.shutdown();
			log.info("Thread interrupted");
			throw new Exception("Interruption error");
		} catch (ExecutionException e) {
			studenDetailsExecutor.shutdown();
			log.info("Something went wrong in execution");
			throw new Exception("Executor error");
		} finally {
			studenDetailsExecutor.shutdown();
		}
		return ovos;
	}

	private Collection<Callable<StudentDetailsOutputVO>> createProcessors(StudentDetailsInputVO ivo) {
		Collection<Callable<StudentDetailsOutputVO>> processWorkers = new ArrayList<>();
		for (String id : ivo.getId()) {
			processWorkers.add(new StudentDetailsExecutor(helper, id));
		}
		return processWorkers;
	}

}
