package com.github.tumusx.todo.project.common


sealed class ResultDataSourceUtil<T>(val message: String? = null, val dataResult: T? = null) {
    class SuccessResultDataSourceDataSourceUtil<T>(dataResult: T) : ResultDataSourceUtil<T>(dataResult = dataResult)
    class ErrorResultDataSourceDataSourceUtil<T>(message: String? = null, dataResult: T? = null) : ResultDataSourceUtil<T>(message, dataResult)
}