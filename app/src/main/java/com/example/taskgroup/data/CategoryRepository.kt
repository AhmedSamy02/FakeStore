import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.taskgroup.data.CategoryApi
import com.example.taskgroup.data.Product

class CategoryProductsPagingSource(
    private val api: CategoryApi,
    private val categoryId: Int,
    private val pageSize: Int
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val products = api.getProductsByCategory(categoryId, page, pageSize)
            LoadResult.Page(
                data = products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (products.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }
}
