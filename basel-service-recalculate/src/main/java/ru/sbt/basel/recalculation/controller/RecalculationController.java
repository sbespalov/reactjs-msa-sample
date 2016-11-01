package ru.sbt.basel.recalculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.sbt.basel.api.common.dto.FindPageResponseDto;
import ru.sbt.basel.recalculation.api.RecalculationServiceApi;
import ru.sbt.basel.recalculation.api.dto.BslFindRecalculationResultListRequest;
import ru.sbt.basel.recalculation.api.dto.BslFindRecalculationResultListResponse;
import ru.sbt.basel.recalculation.domain.RecalculationResultDto;
import ru.sbt.basel.recalculation.service.RecalculationService;

@RestController
@RequestMapping("recalculation")
public class RecalculationController implements RecalculationServiceApi {

	@Autowired
	private RecalculationService recalculationService;

	@RequestMapping(path = "/findRecalculationResultList", method = RequestMethod.GET)
	@CrossOrigin
	@Override
	public BslFindRecalculationResultListResponse findRecalculationResultList(
			BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BslFindRecalculationResultListResponse result = new BslFindRecalculationResultListResponse();

		List<RecalculationResultDto> recalculationResultList = recalculationService.findRecalculationResultList();
		result.setRecalculationResultList(recalculationResultList);

		FindPageResponseDto pageResponse = new FindPageResponseDto();
		result.setPageResponse(pageResponse);

		pageResponse.setPageNumber(1);
		pageResponse.setPageSize(10);
		pageResponse.setTotalCount(1000L);
		
		return result;
	}

}
