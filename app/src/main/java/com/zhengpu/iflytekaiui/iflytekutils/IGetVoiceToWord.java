package com.zhengpu.iflytekaiui.iflytekutils;

import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;

/**
 * sayid ....
 * Created by wengmf on 2017/11/22.
 */

public interface IGetVoiceToWord {


    void getResult(String service, BaseBean result);

    //声音太小10118错误
    void showLowVoice(String result);

    void SpeechOver();

    void SpeechStart();

    void SpeechError(String error);

}
