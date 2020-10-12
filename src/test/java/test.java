import commons.DataHelper;

public class test {
    public static void main(String[] args) {
        DataHelper dataHelper;
        dataHelper = DataHelper.getData();
        System.out.println(dataHelper.getNumber());
    }
}
