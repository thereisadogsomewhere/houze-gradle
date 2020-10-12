package io.houze.agent.web.employee;

public class EmployeeData {
    public static class Form {
        public static class NewEmployee {
            public static final String USERNAME = "autotest_";
            public static final String EMPLOYEE_CODE = "HATEST";
            public static final String FULLNAME = "Nguyễn Lê Cường";
            public static final String PASSWORD = "123456";
            public static final String IDCARD = "025333888";
            public static final String PHONE = "093222";
            public static final String START_DATE = "25/08/2020";
        }

        public static class EditEmployee {
            public static final String FULLNAME = "Trần Văn Nhân";
            public static final String IDCARD = "1234567890";
            public static final String START_DATE = "20/07/2020";
            public static final String PASSWORD = "654321";
        }

        public static final String USERNAME_FIELD = "username";
        public static final String PASSWORD_FIELD = "password";
        public static final String FULLNAME_FIELD = "fullname";
        public static final String IDCARD_FIELD = "identity_card";
        public static final String PHONE_FIELD = "phone_number";
        public static final String ROLE_FIELD = "role";
        public static final String START_DATE_FIELD = "start_date";
        public static final String EMPLOYEE_CODE_FIELD = "employee_code";

        public static final String ROLE_DROPDOWN = "role";
        public static final String STATUS_DROPDOWN = "is_active";

        public static final String REQUIRED_ERROR_TEXT = "Trường này là bắt buộc.";
        public static final String EXISTED_USERNAME_ERROR_TEXT = "Tên đăng nhập đã được sử dụng";
        public static final String EXISTED_EMPLOYEE_CODE_ERROR_TEXT = "Người dùng có Mã nhân viên đã tồn tại.";
        public static final String EXISTED_PHONE_ERROR_TEXT = "Người dùng có SĐT đã tồn tại.";

        public static final String EXISTED_USERNAME = "dev";
        public static final String EXISTED_EMPLOYEE_CODE = "HA-DEV";
        public static final String EXISTED_PHONE = "111";

        public static final String ROLE_AGENT_VALUE = "AGENT";
        public static final String ROLE_MANAGER_VALUE = "MANAGER";
        public static final String ROLE_ADMIN_VALUE = "ADMIN";
        public static final String ROLE_ACCOUNTANT_VALUE = "ACCOUNTANT";
        public static final String ROLE_RECEPTIONIST_VALUE = "RECEPTIONIST";
    }

    public static class List {
        public static final String EMPLOYEE_COLUMN = "Nhân viên";
        public static final String USERNAME_COLUMN = "Username";
        public static final String PHONE_COLUMN = "SĐT";
        public static final String IDCARD_COLUMN = "CMND";
        public static final String EMPLOYEE_CODE_COLUMN = "Mã nhân viên";


    }
}
