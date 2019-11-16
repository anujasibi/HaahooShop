package com.example.haahooshop;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadImage {
    @Multipart
    @POST("api_shop_app/shop_profile_img/")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image, @Header("Authorization")String Token);
}
