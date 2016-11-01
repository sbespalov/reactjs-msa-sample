package ru.sbt.basel.api.common.dto;

public class FindPageResponseDto {

	public static final int DEFAULT_PAGE_SIZE = 20;

	private Integer pageNumber;
	private Integer pageSize;
	private Long totalCount;

	public FindPageResponseDto() {
		super();
	}

	public FindPageResponseDto(Integer currentPageNumber, Integer currentPageSize, Long count) {
		super();
		this.pageNumber = currentPageNumber;
		this.pageSize = currentPageSize;
		this.totalCount = count;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long count) {
		this.totalCount = count;
	}

}
