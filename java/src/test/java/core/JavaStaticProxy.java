package core;

/**
 * java 静态代理
 * Created by crazysnail on 2016/2/28.
 */
public class JavaStaticProxy {
    @org.junit.Test
    public void testMain() throws Exception {
        BankCount count=new BankCount();
        BankCountProxy bankCountProxy = new BankCountProxy(count);
        bankCountProxy.add(1);
        bankCountProxy.query();
    }

    /**
     *接口类 定义一个账户接口
     */
    private interface Count{
        public long query();
        public boolean add(long num);
    }
    /**
     *委托类 银行账户 实现账户接口
     */
    private class BankCount implements  Count{

        @Override
        public long query() {
            return 100;
        }

        @Override
        public boolean add(long num) {
            return true;
        }
    }
    /**
     *代理类
     */
    private class BankCountProxy implements Count{
        private BankCount bankCount;

        public BankCountProxy(BankCount bankCount) {
            this.bankCount = bankCount;
        }


        @Override
        public long query() {
            System.out.println("代理查询操作");
            return bankCount.query();
        }

        @Override
        public boolean add(long num) {
            System.out.println("代理添加操作");
            return bankCount.add(num);
        }
    }

}
