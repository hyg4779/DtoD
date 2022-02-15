package com.ssafy.dtod.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dtod.dto.RegistCheckOutDto;
import com.ssafy.dtod.model.CheckOut;
import com.ssafy.dtod.model.User;
import com.ssafy.dtod.repository.CheckOutRepository;

@Service
public class CheckOutService {

	@Autowired
	private CheckOutRepository checkoutRepository;
	
	
	@Transactional
	public CheckOut registCheckOut(RegistCheckOutDto dto) {
		CheckOut duplicateCheck = new CheckOut();
		CheckOut checkout = CheckOut.builder()
				.user(dto.getUser())
				.checkoutDate(LocalDateTime.now())
				.build();
		List<CheckOut> allList = checkoutRepository.findAll();
		for(int i=0; i<allList.size(); i++) {
			if(checkout.getUser().getUserId() == allList.get(i).getUser().getUserId() && allList.get(i).getCheckoutDate().toString().substring(0,10).equals(checkout.getCheckoutDate().toString().substring(0,10))) {
				return duplicateCheck;
			}
		}
		return checkoutRepository.save(checkout);
	}
	
	@Transactional
	public List<LocalDateTime> myCheckOut(User user){
		List<CheckOut> allList = checkoutRepository.findAll();
		List<LocalDateTime> myCheck = new ArrayList<LocalDateTime>();
		for(int i=0; i<allList.size(); i++) {
			if(user.getUserId() == allList.get(i).getUser().getUserId()) {
				myCheck.add(allList.get(i).getCheckoutDate());
			}
		}
//		Set<LocalDateTime> set = new HashSet<LocalDateTime>(myCheck);
//		myCheck = new ArrayList<LocalDateTime>(set);
		return myCheck;
	}
}
