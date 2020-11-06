package com.tingyu.tongmeng.edu.vod.utils;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.tingyu.tongmeng.edu.vod.properties.AliyunVodProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author essionshy
 * @Create 2020/10/28 19:23
 * @Version tongmeng-edu
 */
public class VideoUtils {

    private static DefaultAcsClient client;

    static {
        try {
            client = InitialVodClient.initVodClient(
                    AliyunVodProperties.ACCESS_KEY, AliyunVodProperties.KEY_SECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static String uploadVideo(MultipartFile file) {
        String videoId = "";
        try {

            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    AliyunVodProperties.ACCESS_KEY, AliyunVodProperties.KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            if (response.isSuccess()) {
                videoId = response.getVideoId();    //请求视频点播服务的请求ID
            } else {
                videoId = response.getVideoId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoId;
    }

    /**
     * @return
     */
    public static String delete(String videoId) {


        String requestId = "";
        try {

            //创建视频删除请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //创建视频删除响应对象
            DeleteVideoResponse response = new DeleteVideoResponse();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoId);
            response = client.getAcsResponse(request);
            requestId = response.getRequestId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return requestId;
    }

    /**
     * 批量删除
     *
     * @param videoIds
     * @return
     */
    public static String deleteByBatch(String videoIds) {

        return delete(videoIds);
    }

    public static String getAuth(String vid) {

        String result = "";

        //创建获取视频凭证请求对象
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//创建获取视频凭证响应对象
        GetVideoPlayAuthResponse response;
        //设置请求参数 视频Id
        request.setVideoId(vid);
        try {
            response = client.getAcsResponse(request);

            result = response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return result;
    }
}
