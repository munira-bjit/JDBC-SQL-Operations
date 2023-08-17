package bjit.academy;

public class Main {

    public static void main(String[] args) {
        DBOperations dbObj = new DBOperations();
        dbObj.doConnectDB();
        dbObj.fetchData();
        dbObj.filterByID();
        dbObj.filterByAgeAndSalary();
        dbObj.averageSalary();
        dbObj.joinAndFilterByDepartment();
    }
}
