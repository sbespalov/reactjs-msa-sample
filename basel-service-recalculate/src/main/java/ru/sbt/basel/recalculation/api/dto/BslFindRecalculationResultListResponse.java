package ru.sbt.basel.recalculation.api.dto;

import java.util.ArrayList;
import java.util.List;

import ru.sbt.basel.api.common.dto.FindPageResponseDto;
import ru.sbt.basel.recalculation.domain.RecalculationResultDto;

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
