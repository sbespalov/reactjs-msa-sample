package com.sbt.basel.service.recalculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbt.basel.service.api.common.dto.FindPageRequestDto;
import com.sbt.basel.service.api.common.dto.FindPageResponseDto;
import com.sbt.basel.service.recalculation.api.RecalculationServiceApi;
import com.sbt.basel.service.recalculation.api.dto.BslFindRecalculationResultListRequest;
import com.sbt.basel.service.recalculation.api.dto.BslFindRecalculationResultListResponse;
import com.sbt.basel.service.recalculation.domain.RecalculationResultDto;
import com.sbt.basel.service.recalculation.service.RecalculationService;

@RestController
@RequestMapping("recalculation")
public class RecalculationController implements RecalculationServiceApi {

	@Autowired
	private RecalculationService recalculationService;

	@RequestMapping(path = "/findRecalculationResultList", method = RequestMethod.POST)
	@CrossOrigin
	@Override
	public BslFindRecalculationResultListResponse findRecalculationResultList(
			@RequestBody BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BslFindRecalculationResultListResponse result = new BslFindRecalculationResultListResponse();

		List<RecalculationResultDto> recalculationResultList = recalculationService.findRecalculationResultList();
		result.setRecalculationResultList(recalculationResultList);

		FindPageResponseDto pageResponse = new FindPageResponseDto();
		result.setPageResponse(pageResponse);

		FindPageRequestDto pageRequest = bslfindRecalculationResultListRequest.getPageRequest();

		pageResponse.setPageNumber(pageRequest.getPageNumber());
		pageResponse.setPageSize(pageRequest.getPageSize());
		pageResponse.setTotalCount(1000L);

		return result;
	}

}
