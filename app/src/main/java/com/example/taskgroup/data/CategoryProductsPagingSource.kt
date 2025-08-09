package com.example.taskgroup.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class CategoryProductsPagingSource(
    private val api: CategoryApi,
    private val categoryId: Int,
    private val pageSize: Int
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val offset = params.key ?: 0
        val limit = params.loadSize.coerceAtLeast(pageSize)

        return try {
            val response = api.getProductsByCategory(
                categoryId = categoryId,
                offset = offset,
                limit = limit
            )

            val nextKey = if (response.isEmpty()) null else offset + response.size

            LoadResult.Page(
                data = response,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = nextKey
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(pageSize) ?: page?.nextKey?.minus(pageSize)
        }
    }
}
