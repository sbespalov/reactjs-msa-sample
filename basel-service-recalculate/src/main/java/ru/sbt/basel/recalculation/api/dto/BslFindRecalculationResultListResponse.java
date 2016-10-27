package ru.sbt.basel.recalculation.api.dto;

import java.util.ArrayList;
import java.util.List;

import ru.sbt.basel.recalculation.domain.RecalculationResultDto;

public class BslFindRecalculationResultListResponse {

	private List<RecalculationResultDto> recalculationResultList = new ArrayList<>();

	public List<RecalculationResultDto> getRecalculationResultList() {
		return recalculationResultList;
	}

	public void setRecalculationResultList(List<RecalculationResultDto> recalculationResultList) {
		this.recalculationResultList = recalculationResultList;
	}

}
