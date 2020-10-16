package commons;

public class GlobalConstants {
    public static final String ROOT_FOLDER = System.getProperty("user.dir");
    public static final long  LONG_TIMEOUT = 10;
    public static final long SHORT_TIMEOUT = 3;
    public static final String BROWSER_LOG_FOLDER = ROOT_FOLDER + "\\browserLog";
    public static final String DOWNLOAD_FOLDER = ROOT_FOLDER + "\\downloadFiles";
    public static final String UPLOAD_FOLDER = ROOT_FOLDER + "\\src\\main\\resources\\uploadFiles\\";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "123456";
    public static final String ACTION_EDIT = "Cập nhật";
    public static final String ACTION_CHANGE_PASSWORD = "Đổi mật khẩu";
    public static final String FIRST_ROW = "1";
    public static final String BASE_URL_HA_PRO_QA = "https://qa.agent-web.houze.io";
}
