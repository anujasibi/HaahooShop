package com.example.haahooshop;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @Multipart
    @POST("api_shop_app/shop_pdt_img/")
    Call<ResponseBody> uploadImag(@Part MultipartBody.Part pdt_image, @Header("Authorization")String Token,@Part("id") RequestBody id);
    @Multipart
    @POST("api_shop_app/branch_profile_img//")
    Call<ResponseBody> uploadIma(@Part MultipartBody.Part shop_image, @Header("Authorization")String Token,@Part("id") RequestBody id);
}


