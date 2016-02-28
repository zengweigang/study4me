package core;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**java 动态代理
 * Created by crazysnail on 2016/2/28.
 */
public class JavaDynamicProxy {
    @Test
    public void testMain() throws Exception {
        BankCount bankCount = new BankCount();
        CommonProxy proxy = new CommonProxy();
        Count count = (Count)proxy.bind(bankCount);
        count.query();
        count.add(1);
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
    private class CommonProxy implements InvocationHandler{
        private Object target;

        public Object bind(Object target) {
            this.target = target;
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            Object result = method.invoke(target, args);
            System.out.println("after");
            return result;
        }
    }
}
