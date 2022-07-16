package com.example.exprimonsnousapp.retrofit;

import com.example.exprimonsnousapp.models.IdCommunity;
import com.example.exprimonsnousapp.models.IdPost;
import com.example.exprimonsnousapp.models.IdVote;
import com.example.exprimonsnousapp.models.NewAccount;
import com.example.exprimonsnousapp.models.NewAccountResponse;
import com.example.exprimonsnousapp.models.NewPost;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.UserLoginCreds;
import com.example.exprimonsnousapp.models.UserUpdateResponse;
import com.example.exprimonsnousapp.models.UserUpdatedInfos;
import com.example.exprimonsnousapp.models.Vote;
import com.example.exprimonsnousapp.models.VoteOption;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiInterface {
    //@GET("/user/login")
    @HTTP(method = "POST", path = "/user/login", hasBody = true)
    Call<UserCreds> getUserCreds(@Body UserLoginCreds userLoginCreds);

    //UPDATE USER INFOS
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "PUT", path = "/user/infos/update", hasBody = true)
    Call<UserUpdateResponse> updateUserInfo(@Body UserUpdatedInfos userUpdatedInfos);

    // GET LISTS OF VOTES FROM A COMMUNITY
    @HTTP(method = "POST", path = "/vote/voteListAndroid", hasBody = true)
    Call<List<Vote>> getVotesFromCommunity(@Body IdCommunity idCommunity);

    // POST A NEW POST
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/post/create", hasBody = true)
    Call<Object> postPost(@Body NewPost newPost);

    // LIKE A POST
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/post/like", hasBody = true)
    Call<Object> likePost(@Body IdPost idPost);


    // DISLIKE A POST
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/post/dislike", hasBody = true)
    Call<Object> dislikePost(@Body IdPost idPost);


    // GET NUMBER OF COMMENTS ON A POST
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "GET", path = "/comment/count/", hasBody = false)
    Call<Object> getNbCommentAPI(@Path("idPost") int idPost);


    // GET NUMBER OF REWARDS ON A POST

    // GET VOTE OPTIONS FROM AN idVote
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/vote/options", hasBody = true)
    Call<VoteOption> getVoteOptions(@Body IdVote idVote);

    // GET VOTE OPTIONS FROM AN idVote
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/vote/infos", hasBody = true)
    Call<List<Vote>> getVote(@Body IdVote idVote);

    //CREATE ACCOUNT
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/user/register", hasBody = true)
    Call<NewAccountResponse> userRegister(@Body NewAccount newAccount);

    //CREATE ACCOUNT
    @Headers({"Content-Type: application/json"})
    @HTTP(method = "POST", path = "/invite/getCommunity/{code}", hasBody = true)
    Call<IdCommunity> extractIdCommunity(@Path("code") int code);
}