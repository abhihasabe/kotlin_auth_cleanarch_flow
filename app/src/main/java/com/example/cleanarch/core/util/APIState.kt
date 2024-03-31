sealed class APIState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : APIState<T>(data)
    class Loading<T>(data: T? = null) : APIState<T>(data)
    class Error<T>(message: String, data: T? = null) : APIState<T>(data, message)
}