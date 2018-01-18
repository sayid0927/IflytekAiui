/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhengpu.watch.presenter.impl;


import com.orhanobut.logger.Logger;
import com.zhengpu.watch.api.Api;
import com.zhengpu.watch.base.RxPresenter;
import com.zhengpu.watch.iflytekbean.request.AppUpdateModel;
import com.zhengpu.watch.presenter.contract.MainContract;
import com.zhengpu.watch.utils.DeviceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivityPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {

    private Api Api;
    public static boolean isLastSyncUpdateed = false;

    @Inject
    public MainActivityPresenter(Api Api) {
        this.Api = Api;
    }


//    @Override
//    public void Apk_Update(Map<String, String> map) {
//
//        Subscription rxSubscription = Api.Fetch_Apk_Update_Info(map).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AppUpdateModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(AppUpdateModel appUpdateModel) {
//                        if (appUpdateModel.getApk_download_path()!= null && mView != null) {
//                            Logger.d(appUpdateModel);
//                            mView.Apk_Update_Info(appUpdateModel);
//                        }
//                    }
//                });
//        addSubscrebe(rxSubscription);
//    }
//
//    @Override
//    public void Apk_Update_Path(String url) {
//        Subscription rxSubscription = Api.Fetch_Apk_Update_Path(url).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response<ResponseBody>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.d(e.toString());
//                    }
//
//                    @Override
//                    public void onNext(Response<ResponseBody> responseBodyResponse) {
//                        try {
//                            File file= saveFile(responseBodyResponse);
//                            if(file!=null){
//                                mView.Apk_Update_Path(file);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        addSubscrebe(rxSubscription);
//    }
//
//    private  String destFileName = System.currentTimeMillis() + ".apk";
//    public File saveFile(Response<ResponseBody> response) throws Exception {
//        String  destFileDir = DeviceUtils.getSDPath();
//        InputStream in = null;
//        FileOutputStream out = null;
//        byte[] buf = new byte[1024];
//        int len;
//        try {
//            File dir = new File(destFileDir);
//            if (!dir.exists()) {// 如果文件不存在新建一个
//                dir.mkdirs();
//            }
//            in = response.body().byteStream();
//            File file = new File(dir,destFileName);
//            out = new FileOutputStream(file);
//            while ((len = in.read(buf)) != -1){
//                out.write(buf,0,len);
//            }
//            return file;
//        }catch (Exception e){
//            e.toString();
//        }finally {
//            in.close();
//            out.close();
//        }
//        return null;
//    }
}
