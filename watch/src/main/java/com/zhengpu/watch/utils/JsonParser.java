package com.zhengpu.watch.utils;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhengpu.watch.iflytekbean.BaikeBean;
import com.zhengpu.watch.iflytekbean.CalcBean;
import com.zhengpu.watch.iflytekbean.CmdBean;
import com.zhengpu.watch.iflytekbean.CustomBaikeBean;
import com.zhengpu.watch.iflytekbean.DatetimeBean;
import com.zhengpu.watch.iflytekbean.FlightBean;
import com.zhengpu.watch.iflytekbean.JokeBean;
import com.zhengpu.watch.iflytekbean.MusicXBean;
import com.zhengpu.watch.iflytekbean.NewsBean;
import com.zhengpu.watch.iflytekbean.OpenAppBean;
import com.zhengpu.watch.iflytekbean.OpenQABean;
import com.zhengpu.watch.iflytekbean.PoetryBean;
import com.zhengpu.watch.iflytekbean.R4Bean;
import com.zhengpu.watch.iflytekbean.RobotCommandBean;
import com.zhengpu.watch.iflytekbean.StoryBean;
import com.zhengpu.watch.iflytekbean.VideoBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import java.lang.reflect.Type;

public class JsonParser {


   public static CalcBean parseResultCalcBean(String json) {

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


     public   static BaikeBean parseResultBaikeBean(String json) {
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


    public static DatetimeBean parseResultDatetimeBean(String json) {
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


    public   static WeatherBean parseResultWeatherBean(String json) {
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


    public static OpenQABean parseResultOpenQABean(String json) {
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

   public   static NewsBean parseResultNewsBean(String json) {
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


    public   static StoryBean parseResultStoryBean(String json) {
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

    public static JokeBean parseResultJokeBean(String json) {
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


    public static PoetryBean parseResultPoetryBean(String json) {
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


   public   static FlightBean parseResultFlightoBean(String json) {
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


    public static R4Bean parseResultR4Bean(String json) {
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


   public   static CustomBaikeBean parseResultCustomBaikeBean(String json) {
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



    static RobotCommandBean parseResultRobotCommandBean(String json) {
        RobotCommandBean robotCommandBean = new RobotCommandBean();
        try {
            Type type = new TypeToken<RobotCommandBean>() {
            }.getType();
            robotCommandBean = new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return robotCommandBean;
    }
}
