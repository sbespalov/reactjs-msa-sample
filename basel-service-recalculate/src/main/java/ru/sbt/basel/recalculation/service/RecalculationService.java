package ru.sbt.basel.recalculation.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ru.sbt.basel.recalculation.domain.RecalculationResultDto;

@Service
public class RecalculationService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<RecalculationResultDto> findRecalculationResultList() {

		jdbcTemplate.execute("select 1 from dual");

		List<RecalculationResultDto> result = new ArrayList<>();

		RecalculationResultDto item = new RecalculationResultDto();
		result.add(item);

		item.setAskCalculateDetail("askCalculateDetail");
		item.setBidCalculateResult("bidCalculateResult");
		item.setDate(new Date());
		item.setSecurity("security");

		item = new RecalculationResultDto();
		result.add(item);

		item.setAskCalculateDetail("askCalculateDetail");
		item.setBidCalculateResult("bidCalculateResult");
		item.setDate(new Date());
		item.setSecurity("security");

		item = new RecalculationResultDto();
		result.add(item);

		item.setAskCalculateDetail("askCalculateDetail");
		item.setBidCalculateResult("bidCalculateResult");
		item.setDate(new Date());
		item.setSecurity("security");

		item = new RecalculationResultDto();
		result.add(item);

		item.setAskCalculateDetail("askCalculateDetail");
		item.setBidCalculateResult("bidCalculateResult");
		item.setDate(new Date());
		item.setSecurity("security");

		return result;
	}

}
