package com.sbt.basel.service.recalculation.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.sbt.basel.service.api.common.dto.FindPageResponseDto;
import com.sbt.basel.service.recalculation.domain.RecalculationResultDto;

public class BslFindRecalculationResultListResponse {

	private FindPageResponseDto pageResponse = new FindPageResponseDto() {

		@Override
		public Integer getPageNumber() {
			return 1;
		}

		@Override
		public Integer getPageSize() {
			return recalculationResultList.size();
		}

		@Override
		public Long getTotalCount() {
			return Long.valueOf(recalculationResultList.size());
		}

	};

	private List<RecalculationResultDto> recalculationResultList = new ArrayList<>();

	public List<RecalculationResultDto> getRecalculationResultList() {
		return recalculationResultList;
	}

	public void setRecalculationResultList(List<RecalculationResultDto> recalculationResultList) {
		this.recalculationResultList = recalculationResultList;
	}

	public FindPageResponseDto getPageResponse() {
		return pageResponse;
	}

	public void setPageResponse(FindPageResponseDto pageResponse) {
		this.pageResponse = pageResponse;
	}

}
