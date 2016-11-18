package com.sbt.basel.service.recalculation.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbt.basel.service.recalculation.domain.RecalculationResultDto;

@Service
public class RecalculationService {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	public List<RecalculationResultDto> findRecalculationResultList() {

		//jdbcTemplate.execute("select 1 from dual");

		List<RecalculationResultDto> result = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			RecalculationResultDto item = new RecalculationResultDto();
			result.add(item);

			item.setAskCalculateDetail("askCalculateDetail");
			item.setBidCalculateResult("bidCalculateResult");
			item.setDate(new Date());
			item.setSecurity("security");
		}

		return result;
	}

}
