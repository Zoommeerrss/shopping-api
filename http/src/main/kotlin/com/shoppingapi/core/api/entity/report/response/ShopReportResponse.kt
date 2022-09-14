package com.shoppingapi.core.api.entity.report.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@JsonIgnoreProperties(
    ignoreUnknown = true
)
@JsonInclude(
    JsonInclude.Include.NON_NULL
)
@Schema(name = "ShopReportResponse")
data class ShopReportResponse(

    @field:Schema(name = "count")
    var count: Long?,

    @field:Schema(name = "total")
    var total: Double?,

    @field:Schema(name = "mean")
    var mean: Double?,
)
