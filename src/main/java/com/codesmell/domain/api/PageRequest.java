package com.codesmell.domain.api;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    @QueryParam("pageNum")
    private Integer pageNum = 1;

    @QueryParam("pageSize")
    private Integer pageSize = 5;

    @QueryParam("sort")
    private SortDirection sort = SortDirection.DESC;

    @QueryParam("sortField")
    private String sortField = "createdDate";

}
