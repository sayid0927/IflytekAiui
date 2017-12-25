package com.zhengpu.iflytekaiui.iflytekutils;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhengpu.iflytekaiui.iflytekbean.BaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekbean.CmdBean;
import com.zhengpu.iflytekaiui.iflytekbean.CustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.iflytekbean.FlightBean;
import com.zhengpu.iflytekaiui.iflytekbean.JokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.NewsBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenAppBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenQABean;
import com.zhengpu.iflytekaiui.iflytekbean.PoetryBean;
import com.zhengpu.iflytekaiui.iflytekbean.R4Bean;
import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.CustomMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfCustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicResBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.TianJokeBean;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;

public class JsonParser {




    static CalcBean parseResultCalc(String json) {

        CalcBean calcBean = new CalcBean();
        try {
            Type type = new TypeToken<CalcBean>() {
            }.getType();
            calcBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calcBean;
    }


     static BaikeBean parseResultBaikeBean(String json) {
        BaikeBean baikeBean = new BaikeBean();
        try {
            Type type = new TypeToken<BaikeBean>() {
            }.getType();
            baikeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baikeBean;
    }


    static DatetimeBean parseResultDatetimeBean(String json) {
        DatetimeBean datetimeBean = new DatetimeBean();
        try {
            Type type = new TypeToken<DatetimeBean>() {
            }.getType();
            datetimeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datetimeBean;
    }


    public static WeatherBean parseResultWeatherBean(String json) {
        WeatherBean weatherBean = new WeatherBean();
        try {
            Type type = new TypeToken<WeatherBean>() {
            }.getType();
            weatherBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherBean;
    }


    static OpenAppBean parseResultOpenAppBean(String json) {
        OpenAppBean openAppBean = new OpenAppBean();
        try {
            Type type = new TypeToken<OpenAppBean>() {
            }.getType();
            openAppBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openAppBean;
    }


    static MusicXBean parseResultMusicXBean(String json) {
        MusicXBean musicXBean = new MusicXBean();
        try {
            Type type = new TypeToken<MusicXBean>() {
            }.getType();
            musicXBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return musicXBean;
    }


    static OpenQABean parseResultOpenQABean(String json) {
        OpenQABean openQABean = new OpenQABean();
        try {
            Type type = new TypeToken<OpenQABean>() {
            }.getType();
            openQABean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openQABean;
    }

    static NewsBean parseResultNewsBean(String json) {
        NewsBean newsBean = new NewsBean();
        try {
            Type type = new TypeToken<NewsBean>() {
            }.getType();
            newsBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsBean;
    }


    static StoryBean parseResultStoryBean(String json) {
        StoryBean storyBean = new StoryBean();
        try {
            Type type = new TypeToken<StoryBean>() {
            }.getType();
            storyBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storyBean;
    }

    static JokeBean parseResultJokeBean(String json) {
        JokeBean jokeBean = new JokeBean();
        try {
            Type type = new TypeToken<JokeBean>() {
            }.getType();
            jokeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jokeBean;
    }


    static PoetryBean parseResultPoetryBean(String json) {
        PoetryBean poetryBean = new PoetryBean();
        try {
            Type type = new TypeToken<PoetryBean>() {
            }.getType();
            poetryBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return poetryBean;
    }


    static VideoBean parseResultVideoBean(String json) {
        VideoBean videoBean = new VideoBean();
        try {
            Type type = new TypeToken<VideoBean>() {
            }.getType();
            videoBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoBean;
    }


    static FlightBean parseResultFlightoBean(String json) {
        FlightBean flightBean = new FlightBean();
        try {
            Type type = new TypeToken<FlightBean>() {
            }.getType();
            flightBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightBean;
    }


    static R4Bean parseResultR4Bean(String json) {
        R4Bean r4Bean = new R4Bean();
        try {
            Type type = new TypeToken<R4Bean>() {
            }.getType();
            r4Bean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r4Bean;
    }

    static CustomMusicBean parseResultCustomMusicBean(String json) {
        CustomMusicBean customMusicBean = new CustomMusicBean();
        try {
            Type type = new TypeToken<CustomMusicBean>() {
            }.getType();
            customMusicBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customMusicBean;
    }


    static CmdBean parseResultCmdBean(String json) {
        CmdBean cmdBean = new CmdBean();
        try {
            Type type = new TypeToken<CmdBean>() {
            }.getType();
            cmdBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmdBean;
    }


    static CustomBaikeBean parseResultCustomBaikeBean(String json) {
        CustomBaikeBean customBaikeBean = new CustomBaikeBean();
        try {
            Type type = new TypeToken<CustomBaikeBean>() {
            }.getType();
            customBaikeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customBaikeBean;
    }



    public static IfCustomBaikeBean parseResultIfCustomBaikeBean(String json) {
        IfCustomBaikeBean ifCustomBaikeBean = new IfCustomBaikeBean();
        try {
            Type type = new TypeToken<IfCustomBaikeBean>() {
            }.getType();
            ifCustomBaikeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifCustomBaikeBean;
    }

    public static IfMusicBean parseResultIfMusicBean(String json) {
        IfMusicBean ifMusicBean = new IfMusicBean();
        try {
            Type type = new TypeToken<IfMusicBean>() {
            }.getType();
            ifMusicBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifMusicBean;
    }

    public static IfMusicResBean parseResultIfMusicResBean(String json) {
        IfMusicResBean ifMusicResBean = new IfMusicResBean();
        try {
            Type type = new TypeToken<IfMusicResBean>() {
            }.getType();
            ifMusicResBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifMusicResBean;
    }

    public static TianJokeBean parseResultTianJokeBean(String json) {
        TianJokeBean tianJokeBean = new TianJokeBean();
        try {
            Type type = new TypeToken<TianJokeBean>() {
            }.getType();
            tianJokeBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tianJokeBean;
    }






}
