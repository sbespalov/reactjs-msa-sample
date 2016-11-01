package ru.sbt.basel.recalculation.api.dto;

import ru.sbt.basel.api.common.dto.FindPageRequestDto;

public class BslFindRecalculationResultListRequest {

	private FindPageRequestDto pageRequest = new FindPageRequestDto();

	public FindPageRequestDto getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(FindPageRequestDto pageRequest) {
		this.pageRequest = pageRequest;
	}

}
