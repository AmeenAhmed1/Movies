package com.ameen.movies.data.datasource

import androidx.paging.PagingConfig
import com.ameen.movies.core.util.PAGE_SIZE

val PAGE_CONFIG =
    PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false
    )
