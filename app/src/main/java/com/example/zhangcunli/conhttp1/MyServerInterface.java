package com.example.zhangcunli.conhttp1;



import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhangcunli on 2016/5/12.
 */
public interface MyServerInterface {
    @GET("BookController/books.con")
    Call<List<Book>> bookDetais();

    @GET("BookController/{bookName}/bk.con")
    Call<List<Book>> bookCanshu(@Path("bookName") String bookName);
}
