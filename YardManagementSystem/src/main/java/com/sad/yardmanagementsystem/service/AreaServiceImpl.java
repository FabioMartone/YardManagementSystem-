package com.sad.yardmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.repository.RepositoryArea;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	RepositoryArea areaRepository;
	
	public void add_area(Area a) {
		
		areaRepository.save(a);
	}
}
