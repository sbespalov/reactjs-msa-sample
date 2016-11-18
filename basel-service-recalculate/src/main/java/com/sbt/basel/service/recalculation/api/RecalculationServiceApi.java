package com.sbt.basel.service.recalculation.api;

import com.sbt.basel.service.recalculation.api.dto.BslFindRecalculationResultListRequest;
import com.sbt.basel.service.recalculation.api.dto.BslFindRecalculationResultListResponse;

public interface RecalculationServiceApi {

	public BslFindRecalculationResultListResponse findRecalculationResultList(
			BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest);

}
