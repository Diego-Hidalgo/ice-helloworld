public class ResponderI implements Trying.Response {

    public int giveResponse(String info, String msg, com.zeroc.Ice.Current current) {
        System.out.println(info);
        if(isPositiveNumber(msg)) {
            return fib(Integer.parseInt(msg));
        }
        System.out.println(msg);
        return 0;
    }

    private int fib(int n) {
        int num0 = 0, num1 = 1, sum = 0;
        System.out.printf("fib(%s) = %s %n", 0, 0);
        if(n > 0){
            sum = 1;
            System.out.printf("fib(%s) = %s %n", 1, 1);
        }
        for (int i = 2; i <= n; i++) {
            sum = num0 + num1;
            num0 = num1;
            num1 = sum;
            System.out.printf("fib(%s) = %s %n", i, sum);
        }
        return sum;
    }

    private boolean isPositiveNumber(String msg) {
        return msg.matches("^+([0-9]\\d*)$");
    }

}
