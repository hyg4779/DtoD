package com.ssafy.dtod.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dtod.model.Techstack;
import com.ssafy.dtod.repository.TechstackRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/tech")
@Api(tags = {"기술스택 API"})

public class TechstackController {
	
	@Autowired
	private TechstackRepository techstackRepository;
	
	@PostMapping("/join")
	@ApiOperation(value = "기술스택 등록", notes = "기술스택 생성")
	public Techstack create(@RequestBody Techstack techstack) {
		
		return techstackRepository.save(techstack);
	}
	
	@GetMapping("/{techCode}")
    @ApiOperation(value = "기술스택 코드 조회", notes = "기술스택 코드에 해당하는 기술스택 정보를 조회한다.")
    public Techstack read(@PathVariable int techCode) {
        Optional<Techstack> techOptional = techstackRepository.findById(techCode);
        techOptional.ifPresent(System.out::println);

        return techOptional.get();
    }
	
	
}