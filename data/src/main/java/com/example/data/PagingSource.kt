package com.example.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.PhotoData
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class PagingSource @Inject constructor(
    private val apiService: ApiService,
    private val key: String,
    private val query: String,
) : PagingSource<Int, PhotoData>() {
    private val mapper = Mapper()
    override fun getRefreshKey(state: PagingState<Int, PhotoData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoData> {
        return try {
            val page = params.key ?: 1
            val response = apiService.searchPhoto(query = query, key = key, count = 30)
            val nextKey = if (response.size < 30) null else (page + 1)
            val data = mapper.map(response)
            return LoadResult.Page(
                data = data, prevKey = if (page == 0) null else page - 1, nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}