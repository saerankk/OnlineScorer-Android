package com.liulishuo.engzo.stat;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rantianhua on 17/7/28.
 * a specific stat type, represent retry record data
 */

public class RetryRecordItem extends StatItem {

    public String audioId;
    public String network;
    // the type of XxxExercise
    public String item;
    public long recordStartTime;
    public long responseTime;
    public String errorCode;

    public RetryRecordItem(String audioId, String network, String item) {
        this.type = StateItemType.RETRY_RECORD;
        this.audioId = audioId;
        this.network = network;
        this.item = item;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
            JSONObject data = new JSONObject();
            data.put("audioId", audioId);
            data.put("network", network);
            data.put("item", item);
            data.put("recordStartTime", recordStartTime);
            data.put("responseTime", responseTime);
            data.put("errorCode", errorCode);
            jsonObject.put("data", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public void collectStatPoint(String name, String value) {
        if (name.equals("recordStartTime")) {
            this.recordStartTime = Long.valueOf(value);
        } else if (name.equals("responseTime")) {
            this.responseTime = Long.valueOf(value);
        } else if (name.equals("errorCode")) {
            this.errorCode = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RetryRecordItem that = (RetryRecordItem) o;

        if (recordStartTime != that.recordStartTime) return false;
        if (responseTime != that.responseTime) return false;
        if (!audioId.equals(that.audioId)) return false;
        if (!network.equals(that.network)) return false;
        if (!item.equals(that.item)) return false;
        return errorCode.equals(that.errorCode);

    }

    @Override
    public int hashCode() {
        int result = audioId.hashCode();
        result = 31 * result + network.hashCode();
        result = 31 * result + item.hashCode();
        result = 31 * result + (int) (recordStartTime ^ (recordStartTime >>> 32));
        result = 31 * result + (int) (responseTime ^ (responseTime >>> 32));
        result = 31 * result + errorCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RetryRecordItem{" +
                "type='" + type + '\'' +
                "audioId='" + audioId + '\'' +
                ", network='" + network + '\'' +
                ", item='" + item + '\'' +
                ", recordStartTime=" + recordStartTime +
                ", responseTime=" + responseTime +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
