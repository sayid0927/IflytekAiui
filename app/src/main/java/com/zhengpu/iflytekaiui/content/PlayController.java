package com.zhengpu.iflytekaiui.content;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/1/1 0001.
 */

public class PlayController {

    public int Id;
    public String ;
    public boolean isMale;



    public PlayController() {
    }

    public PlayController(int PlayControllerId, String PlayControllerName, boolean isMale) {
        this.PlayControllerId = PlayControllerId;
        this.PlayControllerName = PlayControllerName;
        this.isMale = isMale;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(PlayControllerId);
        out.writeString(PlayControllerName);
        out.writeInt(isMale ? 1 : 0);

    }

    public static final Parcelable.Creator<PlayController> CREATOR = new Parcelable.Creator<PlayController>() {
        public PlayController createFromParcel(Parcel in) {
            return new PlayController(in);
        }

        public PlayController[] newArray(int size) {
            return new PlayController[size];
        }
    };

    private PlayController(Parcel in) {
        PlayControllerId = in.readInt();
        PlayControllerName = in.readString();
        isMale = in.readInt() == 1;
    }

    @Override
    public String toString() {
        return String.format(
                "[PlayControllerId:%s, PlayControllerName:%s, isMale:%s]",
                PlayControllerId, PlayControllerName, isMale);
    }
}
