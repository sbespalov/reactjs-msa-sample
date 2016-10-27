package ru.sbt.basel.recalculation.api;

import ru.sbt.basel.recalculation.api.dto.BslFindRecalculationResultListRequest;
import ru.sbt.basel.recalculation.api.dto.BslFindRecalculationResultListResponse;

public interface RecalculationServiceApi {

	public BslFindRecalculationResultListResponse findRecalculationResultList(
			BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest);

}
