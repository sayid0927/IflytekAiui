package com.zhengpu.iflytekaiui.iflytekutils;

import com.iflytek.cloud.SpeechError;

/**
 * sayid ....
 * Created by wengmf on 2017/11/22.
 */

public interface IGetWordToVoice {

    void SpeechEnd(String wordsData);

    void SpeechError(SpeechError error);

}
