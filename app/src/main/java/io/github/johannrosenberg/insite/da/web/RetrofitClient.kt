package earth.topdog.android.da.web

import com.google.gson.GsonBuilder
import io.github.johannrosenberg.insite.BuildConfig
import io.github.johannrosenberg.insite.da.web.APIBaseAddress
import io.github.johannrosenberg.insite.da.web.WebAPI
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {

        private var requestHeaders = mutableMapOf<String, String>()

        fun createRetrofitClient(): WebAPI {
            return Retrofit.Builder()
                .baseUrl(APIBaseAddress)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(initializeRetrofit())
                .build().create(WebAPI::class.java)
        }

        private fun initializeRetrofit(): OkHttpClient {

            val httpClient = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                httpClient.readTimeout(1, TimeUnit.HOURS)
            }

            httpClient.addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()

                requestBuilder.header("Accept", "application/json")
                requestBuilder.header("Content-Type", "application/json")

                requestHeaders.forEach { entry ->
                    requestBuilder.header(entry.key, entry.value)
                }

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            return httpClient.build()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter { body: ResponseBody ->
            if (body.source().exhausted()) {
                null
            } else {
                delegate.convert(body)
            }
        } as Converter<ResponseBody, Any?>
    }
}