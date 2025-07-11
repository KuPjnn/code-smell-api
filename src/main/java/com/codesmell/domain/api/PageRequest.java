package com.codesmell.domain.api;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
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
