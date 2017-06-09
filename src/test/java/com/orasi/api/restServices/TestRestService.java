package com.orasi.api.restServices;

import static com.orasi.api.restServices.ResponseCodes.OK;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orasi.api.restServices.Headers.HeaderType;
import com.orasi.api.restServices.exceptions.RestException;
import com.orasi.api.restServices.helpers.PostRequest;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestRestService {
    public final static String basePostsUrl = "https://jsonplaceholder.typicode.com/posts";

    @Features("API")
    @Stories("RestServices")
    @Title("createRestService")
    @Test
    public void createRestService() {
        RestService rest = new RestService();
        Assert.assertNotNull(rest);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendGetRequestOnlyURL")
    @Test
    public void sendGetRequestOnlyURL() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendGetRequest(basePostsUrl).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendGetRequestInvalidURL")
    @Test(expectedExceptions = RestException.class)
    public void sendGetRequestInvalidURL() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendGetRequest("ht://jsplaceholder.typicode.com/posts").getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendGetRequestURLAndHeader")
    @Test
    public void sendGetRequestURLAndHeader() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendGetRequest(basePostsUrl, HeaderType.BASIC_CONVO).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendGetRequestURLHeaderAndQueryParams")
    @Test
    public void sendGetRequestURLHeaderAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendGetRequest(basePostsUrl, HeaderType.JSON, params).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPostRequestURLHeaderAndJson")
    @Test
    public void sendPostRequestURLHeaderAndJson() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPostRequest(basePostsUrl, HeaderType.AUTH, RestService.getJsonFromObject(request)).getStatusCode() == ResponseCodes.CREATED);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPostRequestURLAndQueryParams")
    @Test
    public void sendPostRequestURLAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendPostRequest(basePostsUrl, params).getStatusCode() == ResponseCodes.CREATED);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPostRequestURLHeaderAndQueryParams")
    @Test
    public void sendPostRequestURLHeaderAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendPostRequest(basePostsUrl, HeaderType.JSON, params).getStatusCode() == ResponseCodes.CREATED);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPostRequestURLHeaderJsonAndQueryParams")
    @Test
    public void sendPostRequestURLHeaderJsonAndQueryParams() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPostRequest(basePostsUrl, HeaderType.BASIC_CONVO, params, RestService.getJsonFromObject(request)).getStatusCode() == ResponseCodes.CREATED);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPutRequestURLAndHeader")
    @Test
    public void sendPutRequestURLAndHeader() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPutRequest(basePostsUrl + "/1", HeaderType.AUTH).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPutRequestURLHeaderAndJson")
    @Test
    public void sendPutRequestURLHeaderAndJson() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPutRequest(basePostsUrl + "/1", HeaderType.AUTH, RestService.getJsonFromObject(request)).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPutRequestURLAndJson")
    @Test
    public void sendPutRequestURLAndJson() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPutRequest(basePostsUrl + "/1", RestService.getJsonFromObject(request)).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPutRequestURLAndQueryParams")
    @Test
    public void sendPutRequestURLAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendPutRequest(basePostsUrl + "/1", params).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPutRequestURLHeaderAndQueryParams")
    @Test
    public void sendPutRequestURLHeaderAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendPutRequest(basePostsUrl + "/1", HeaderType.JSON, params).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPutRequestURLHeaderJsonAndQueryParams")
    @Test
    public void sendPutRequestURLHeaderJsonAndQueryParams() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPutRequest(basePostsUrl + "/1", HeaderType.BASIC_CONVO, params, RestService.getJsonFromObject(request)).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPatchRequestURLParamsAndJson")
    @Test
    public void sendPatchRequestURLParamsAndJson() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPatchRequest(basePostsUrl + "/1", params, RestService.getJsonFromObject(request)).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPatchRequestURLHeaderAndJson")
    @Test
    public void sendPatchRequestURLHeaderAndJson() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPatchRequest(basePostsUrl + "/1", HeaderType.AUTH, RestService.getJsonFromObject(request)).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPatchRequestURLAndQueryParams")
    @Test
    public void sendPatchRequestURLAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendPatchRequest(basePostsUrl + "/1", params).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPatchRequestURLHeaderAndQueryParams")
    @Test
    public void sendPatchRequestURLHeaderAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendPatchRequest(basePostsUrl + "/1", HeaderType.JSON, params).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendPatchRequestURLHeaderJsonAndQueryParams")
    @Test
    public void sendPatchRequestURLHeaderJsonAndQueryParams() {
        PostRequest request = new PostRequest();
        request.setUserId(1);
        request.setTitle("blah");
        request.setBody("blah");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));

        RestService rest = new RestService();
        Assert.assertTrue(rest.sendPatchRequest(basePostsUrl + "/1", HeaderType.BASIC_CONVO, params, RestService.getJsonFromObject(request)).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendDeleteRequestURL")
    @Test
    public void sendDeleteRequestURL() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendDeleteRequest(basePostsUrl + "/1").getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendDeleteRequestURLAndHeader")
    @Test
    public void sendDeleteRequestURLAndHeader() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendDeleteRequest(basePostsUrl + "/1", HeaderType.JSON).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendDeleteRequestURLHeaderAndQueryParams")
    @Test
    public void sendDeleteRequestURLHeaderAndQueryParams() {
        RestService rest = new RestService();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", "2"));
        Assert.assertTrue(rest.sendDeleteRequest(basePostsUrl + "/1", HeaderType.JSON, params).getStatusCode() == OK);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("sendOptionsRequestURL")
    @Test
    public void sendOptionsRequestURL() {
        RestService rest = new RestService();
        Assert.assertTrue(rest.sendOptionsRequest(basePostsUrl + "/1").length > 0);
    }

    @Features("API")
    @Stories("RestServices")
    @Title("getJsonFromObjectExpectError")
    @Test(expectedExceptions = RestException.class)
    public void getJsonFromObjectExpectError() {
        RestService rest = new RestService();
        RestService.getJsonFromObject(rest);
    }

}
