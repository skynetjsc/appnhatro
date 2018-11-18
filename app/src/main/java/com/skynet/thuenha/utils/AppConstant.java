package com.skynet.thuenha.utils;

/**
 * Created by DuongKK on 11/30/2017.
 */

public class AppConstant {
    public final static int POSITIVE = 1;
    public final static int NEGATIVE = 0;
    public final static String KEY_SETTING = "setting";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_PROFILE = "profile";
    public static final String KEY_PAGE_OPEN_ORDER = "OP";
    public static final String KEY_PAGE_ACCEPTED_ORDER = "AC";
    public static final String KEY_PAGE_CLOSED_ORDER = "CL";

    public static final int CODE_EXPIRED = 401;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_SERVER_ERROR = 500;
    public static final int CODE_API_ERROR_MAX_ORDER = 2;
    public static final int CODE_API_SUCCESS = 200;
    public static final int CODE_STATUS_NEW = 0;
    public static final int CODE_STATUS_PREPARING = 1;
    public static final int CODE_STATUS_READY_FOR_PICKUP = 2;
    public static final int CODE_STATUS_ACCEPTED = 3;
    public static final int CODE_STATUS_DELIVERY_IN_PROGRESS = 4;
    public static final int CODE_STATUS_DELIVERIED = 5;
    public static final int CODE_STATUS_CANCELLED = 6;

    public static final String STR_STATUS_CANCLE_ACCEPTED = "CA";
    public static final String STR_STATUS_ACCEPTED = "AC";
    public static final String STR_STATUS_DELIVERIED = "DL";
    public static final String STR_STATUS_UNSUCCESSFUL = "CC";
    public static final String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String NAME_PARTEN = "(.*[!@#$%^&*()-+_={}\\[\\]\"':;/\\\\\\|,.~`].*)";
    public static final String KEY_ID_ORDER = "id_order";
    public static final String KEY_TOKEN_FCM = "token_fcm";
    public static final String ACTION_BROADCAST_NOTIFY = "ACTION_BROADCAST_NOTIFY";
    public static final String MSG = "msg";
    public static final String city = "city";
    public static final String district = "district";
    public static final String BUNDLE = "bundle";
    public static final String NOTIFICATION_SOCKET = "noti_socket";
    public static final String INTENT = "intent";
    public static final int AUTO = 1;
    public static final int NOTI = 2;
    public static final String NOTI_ = "noti";
    public static final String GG_KEY = "AIzaSyBgF9OgfJ1TZXH-hnDXrb5AoBX-RHu_sB4";
    public static final String CHAT_ID = "chat_id";
    public static final int CHAT_INT = 3;
    public static final String LANGUAGE = "lang";
    public static final int TYPE_USER = 1;
    public static final String NUM_CARD = "numbercard";
    public static final String NAME_CARD = "nameCard";
    public static final String DDMM_CARD = "dmmCard";
    public static final String CVV_CARD = "cvvCard";
    public static final String JSON = "json";
    public static final String NOTI_ON = "noti_on";
    public static final int STATE_OUT_OF_STORE = 0;
    public static final String LATLNG = "latlng";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String FROM = "from";
    public static final String DES = "des";
    public static final String TYPE_ADDRESS = "address";
    public static final int CODE_REQ_ADDRESS = 1002;
    public static final String ID_BOOKING = "idBooking";
    public static final String LIST_SHIPER = "listShiper";
    public static final String HELPER_CANCLE = "cancle";
    public static final String HELPER_AGREE = "agree";
    public static final String PHONE_CENTER = "0962672240";
    public static final String KEY_TRIP_TIME = "trip_future";
    public static final String KEY_ID = "key_id";
    public static String ID_CHAT = "id_chat";
    public static final int CODE_REQ_BOOKING = 1001;

    public class Value {
        public static final int DEFAULT_LANGUAGE_ID = 0;
    }

    public class RequestCode {
        public static final int CHANGE_LANGUAGE = 10000;
    }
}
