package designpattern.decorate;

/**
 * T恤装饰
 * @author laizy1991@gmail.com
 * @createDate 2015年8月27日
 *
 */
public class TShirts extends Finery {

    public void show() {
        component.show();
        System.out.println("T恤");
    }
    
}
